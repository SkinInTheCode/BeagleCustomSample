dest=origin/master
branch=local/branch_for_pull_request

changed_modules=""

git diff --name-only $dest..$branch | { while read line
    do
      module_name=${line%%/*}

      if [[ ${module_name} != "buildSrc" &amp;&amp;
            ${changed_modules} != *"$module_name"* ]]; then
              changed_modules="${test_modules} ${module_name}"
      fi
    done
}