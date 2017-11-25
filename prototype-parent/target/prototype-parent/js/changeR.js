/**
 * function:generate different checkCode by different click
 * 
 * 函数:用于点击时产生不同的验证码
 */
function changeR(node){
        // 用于点击时产生不同的验证码
        node.src = "randomcode.jpg?time="+new Date().getTime() ;    
    }
