package pintertest.logic

import pintertest.PositionRequest
import pintertest.data.DataStore
import pintertest.PositionResponse
import pintertest.PositionResponse
import pintertest.RectangleWithIndentifer
import pintertest.RectangleWithIndentifer
import pintertest.RectangleWithIndentifer
import pintertest.PositionResponse

object TilePositionSetter {
	
    def calculate(request:PositionRequest, store:DataStore) = {
        val width = imageWidth(request.rectangle.width, request.columnNumber, request.margin)
        val array = new Array[Tuple2[Int,List[RectangleWithIndentifer]]](request.columnNumber);
        for (i <- 0 until request.columnNumber) {
        	array(i) = (0, List[RectangleWithIndentifer]())
        }
        
        // ƒAƒ‹ƒSƒŠƒYƒ€
        // 1. “®“Ilist ‚ð—ñ”•ªìŽŒŠG
        // val lists = new ListBuffer[Int]()
        for(rect <- store.iterator){
            val toAdd = minIndex(array)
            array(toAdd) = add(array(toAdd), rect, width, request.margin)
        }
      
        // new Array[List]();
        // 2. ‚¿‚å‚Á‚Æ•À‚Ñ‘Ö‚¦‚é‚©
        // TODO
        
        PositionResponse(array, request.rectangle.width, width, request.margin, request.columnNumber)
	}
    
    def minIndex(a:Array[Tuple2[Int,List[RectangleWithIndentifer]]]) = {
      var hoge = 0;
      var index = 0
      for(entry <- a ){        
          if(a(hoge)._1 > entry._1){
        	  hoge = index
          }
          index += 1;
      }     
      hoge
    }
    
    def maxIndex(a:Array[Tuple2[Int,List[RectangleWithIndentifer]]]) = {
      var hoge = 0;
      var index = 0
      for(entry <- a ){        
          if(a(hoge)._1 < entry._1){
        	  hoge = index
          }
          index += 1;
      } 
      hoge
    }

        
    private def add(entry:Tuple2[Int,List[RectangleWithIndentifer]], rect:RectangleWithIndentifer, width:Int, margin:Int) = {
        val height = (width.toDouble / rect.width.toDouble * rect.height.toDouble).toInt
        val addRect = RectangleWithIndentifer(rect.id, width, height)
        (entry._1 + height + margin, entry._2 :+ addRect)
    }
    
    def imageWidth(width:Int, colmunNumber:Int, margin:Int):Int = {
        if(width <= 0 || colmunNumber <= 0 ||margin <= 0){
        	throw new IllegalArgumentException("input error")
        }
        if(width < margin * (colmunNumber+1)){
        	throw new IllegalArgumentException("margin is too big")
        }
        
        (width - margin *(colmunNumber+1)) / colmunNumber;
    }
    
	
}