package test

import java.io.File
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import javax.imageio.ImageIO
import java.io.FileOutputStream
import java.awt.RenderingHints
import java.awt.geom.AffineTransform
import pintertest.RectangleWithIndentifer
import pintertest.data.DataStoreFactory
import pintertest.logic.TilePositionSetter
import pintertest.PositionRequest
import pintertest.Rectangle
import pintertest.output.ImageWriter

object Tester extends App{
	val pos = RectangleWithIndentifer("hoge", 100, 200);
	
	val margin = 20;
	val width = 600;
	val columnNumber = 3;
	
	println(pos.width)

	val storeOp = DataStoreFactory.createFromDirectory(new File("/Users/morioka/Pictures/test/20120421"))
	
	// 並べ替え方法とか(もっとインテリジェンスに)
	// 画像の種類増やす
	
	storeOp match {
	  case None => println("noting ")
	  case Some(dataStore) => 
	  	val response = TilePositionSetter.calculate(PositionRequest(columnNumber, margin, Rectangle(width,400)), dataStore)
	  	ImageWriter.write("hoge.jpg", response)
	}

	/*
	def imageResize(image:BufferedImage, width:Int, height:Int):BufferedImage = {
	    val resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    val g = resized.getGraphics
	    val g2d = g.asInstanceOf[Graphics2D]
	    val xform = AffineTransform.getScaleInstance(scale, scale);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                           RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	    g2d.drawImage(image, xform, null);
        g2d.dispose();
        resized;
	}
	*/

	/*
	def result = TilePositionSetter.imageWidth(1000, 3, 40);
	println(TilePositionSetter.imageWidth(1000, 3, 40));	
	*/
}