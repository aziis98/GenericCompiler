var projectIn = {
    'src': {
        type: 'directory',
        'folder': {
            type: 'directory',
            'test.txt': {
                type: 'file'
                content: "package folder; class A { }  class B { }"
            }
        }
    }
}

var projectOut = {
    'out': {
        type: 'directory',
        'folder': {
            type: 'directory',
            'A.java': {
                type: 'file'
                content: "package folder; public class A { }"
            }
            'B.java': {
                type: 'file'
                content: "package folder; public class B { }"
            }
        }
    }
}


