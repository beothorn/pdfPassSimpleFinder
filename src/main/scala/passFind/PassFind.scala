package passFind

import com.itextpdf.text.exceptions.BadPasswordException
import com.itextpdf.text.pdf.PdfReader

object PassFind extends App{
  if (args.length < 1) {
    println("Argument must be a pdf file, second argument is password size and is optional")
    println("if size is 4 passwords from 0000 to 9999 will be tried")
    System.exit(-1)
  }
  var paddingInt = 5
  var padding = "%05d"
  if (args.length == 2) {
    padding = "%0"+args(1)+"d"
    paddingInt = args(1).toInt
  }
  val filename = args(0)
  for(i <- 0 to (scala.math.pow(10, paddingInt).toInt - 1)) {
    var goodPass = true
    val pass = padding.format(i)
    try {
      new PdfReader(filename, pass.getBytes())
    } catch {
      case _: BadPasswordException => goodPass = false
    }
    if(goodPass){
      print(pass)
      System.exit(0)
    }
  }
  print("Nothing found")
}
