
/// define "println" = "System.out.println"
/// define "Table" = "HashMap<String, String>"
/// define "Multi" = "List"
/// replace "(?<!(\w))\[(.+?)\]" = "Arrays.asList($2)"
/// replace "\.(\w+)<(.+)>\(" = ".<$2>$1("
/// replace "return ((.+?)+?);" = "return Arrays.<Object>asList( $1 );"

// replace "(?<![{}\\s\\n])\\n" = ";\\n"

package com.aziis98;