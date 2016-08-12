package custom

import custom.ParquetTools._
import org.apache.hadoop.fs.Path
import parquet.hadoop.ParquetReader

object CustomParquetReader extends App {

  // just for the example, we encapsulated the string (saved simply as a string by CustomParquetWriter)
  // into a case class. The ReadSupport does the conversion directly.
  case class CustomString(value: String)

  // new ParquetReader(...) is deprecated, we must use the builder form
  def parquetReader(path: Path): ParquetReader[CustomString] = {
    ParquetReader.builder[CustomString](new CustomReadSupport, path).build()
  }

  val reader = parquetReader("/tmp/toto.parquet")
  parquetFileIterator(reader).foreach(println)
  reader.close()

}
