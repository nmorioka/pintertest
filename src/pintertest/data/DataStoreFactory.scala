package pintertest.data

import java.io.File

import javax.imageio.ImageIO
import pintertest.RectangleWithIndentifer
import scala.util.Random

object DataStoreFactory {
  
	def createFromDirectory(directory:File):Option[DataStore] = {	  
	  val files = directory.listFiles();
	  // TODO ������store���\������̂��͂��̕ӂ���C�����邱��
	  def array = for{
		  	file <- files 
		  	if file.getName().endsWith(".jpg")
		  	image = ImageIO.read(file)
	  } yield RectangleWithIndentifer(file.getAbsolutePath(), image.getWidth, image.getHeight)
	  
	  
	  println("jpg length : " + array.length)
	  array.length match{
	    case 0 => None
	    case _ => 
	      // shuffle���Ă����Ȃ��ƁA�A�A�f�[�^�̃T�C�Y�ł�����Ă��邱�Ƃ�����
	      val list = Random.shuffle(array.toList)
	      Some(new DataStore(list))
	  }
	}
}

class DataStore(val list:List[RectangleWithIndentifer]){
    private val it = list.iterator;
    
	def length:Int = {
	  list.length;
	}
	
	def iterator = {
	  it
	}	  
	
	def next:RectangleWithIndentifer ={
	  it.next()
	}
}