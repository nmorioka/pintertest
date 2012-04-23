package pintertest

/**
 * ‚Ç‚¤‚¢‚¤•—‚É”z’u‚¢‚·‚é‚©‚Ìİ’èclass
 */
case class PositionRequest(columnNumber:Int, margin:Int, rectangle:Rectangle) {
}

case class Rectangle(width:Int, height:Int){
}

case class RectangleWithIndentifer(id:String, width:Int, height:Int){
}