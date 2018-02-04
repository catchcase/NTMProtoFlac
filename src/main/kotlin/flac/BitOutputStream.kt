package flac

import java.io.*

class BitOutputStream(private val out: OutputStream) : AutoCloseable {
	private var buffer = 0L
	private var bufferLength: Int = 0

	fun writeInt(bits: Int, value: Int) {
		println("Bits: $bits  Value: $value")

		buffer = buffer shl bits or ((value and ((1L shl bits) - 1).toInt()).toLong())
		bufferLength += bits

		while (bufferLength >= 8) {
			bufferLength -= 8
			val b = buffer.ushr(bufferLength).toInt() and 0xFF
			out.write(b)
		}
	}

	override fun close() {
		out.close()
	}
}