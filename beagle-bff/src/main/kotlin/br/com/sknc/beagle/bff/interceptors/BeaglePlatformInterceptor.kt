package br.com.sknc.beagle.bff.interceptors

import br.com.zup.beagle.platform.BeaglePlatformUtil
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.util.ContentCachingResponseWrapper
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BeaglePlatformInterceptor(private val objectMapper: ObjectMapper) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.setAttribute(
            BeaglePlatformUtil.BEAGLE_PLATFORM_HEADER,
            request.getHeader(BeaglePlatformUtil.BEAGLE_PLATFORM_HEADER)
        )
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        val responseWrapper = (response as ContentCachingResponseWrapper)
        val jsonTree = this.objectMapper.readTree(responseWrapper.contentAsByteArray)

        request.getHeader(BeaglePlatformUtil.BEAGLE_PLATFORM_HEADER)?.let {
            if (it == "IOS"){
                jsonTree.findParents("cornerRadius")?.forEach { jsonNode ->
                    if( jsonNode is ObjectNode)
                        jsonNode.remove("cornerRadius")
                }
            }
        }

        val jsonData = jsonTree.toPrettyString()
        responseWrapper.resetBuffer()
        responseWrapper.outputStream.write(jsonData.toByteArray())
        responseWrapper.setContentLength(jsonData.length)
    }
}

@Configuration
class AppWebMvcConfig(
    private val beaglePlatformInterceptor: BeaglePlatformInterceptor
) : WebMvcConfigurer{

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(beaglePlatformInterceptor)
    }
}