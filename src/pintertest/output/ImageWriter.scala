package pintertest.output

import java.io.File
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.FileOutputStream
import java.awt.RenderingHints
import java.awt.geom.AffineTransform
import pintertest.RectangleWithIndentifer
import pintertest.logic.TilePositionSetter
import pintertest.PositionResponse


object ImageWriter {
	def write(fileName:String, response:PositionResponse){
	  	val maxIndex = TilePositionSetter.maxIndex(response.lists);
	 	val maxHeight = response.lists(maxIndex)._1;
		// val imageWidth = TilePositionSetter.imageWidth(response.width, response.columnNumber, response.margin);
	  
	    val image = new BufferedImage(response.width, maxHeight + (response.margin*2), BufferedImage.TYPE_INT_BGR);
		println(image)
	  	val g = image.getGraphics
		val g2d = g.asInstanceOf[Graphics2D]
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    				     RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	  		 
		var xpos = response.margin;
	  	for(column <- response.lists){
	  	    var ypos = response.margin;
	      	for(row <- column._2){
	    		def drawImage = ImageIO.read(new File(row.id))

	    		g2d.drawImage(drawImage, xpos, ypos, row.width, row.height, null)
	    		ypos += row.height + response.margin
	  	    }
	      	xpos += response.imageWidth + response.margin
	  	}
	  
	  	g2d.dispose()
	  	ImageIO.write(image, "jpg", new FileOutputStream(fileName))
	}
}