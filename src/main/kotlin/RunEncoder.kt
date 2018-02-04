
import flac.*
import java.io.*

/**
 * This application creates an instance of the Encoder class and invokes the encode() function
 * on a BitOutputStream pointing to the given path. If the file does not exist, it is created.
 * If it already exists, it is overwritten. The output file/path can be adjusted.
 */
fun main(args: Array<String>) {

	val encoder = Encoder()
	val outputFile = if (args.getOrNull(0).isNullOrEmpty()) "outputFile.flac" else args[0]

	BitOutputStream(BufferedOutputStream(FileOutputStream(outputFile))).use { out -> encoder.encode(out) }
}