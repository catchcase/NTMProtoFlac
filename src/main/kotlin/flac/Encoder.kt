package flac

class Encoder(
		private val sampleRate: Int = 8000,
		private val sampleDepth: Int = 16,
		private val numChannels: Int = 2
) {
	private val fLaC = 0x664C6143

	private val STREAMINFO = 0
	private val PADDING = 1
	private val APPLICATION = 2
	private val SEEKTABLE = 3
	private val VORBIS_COMMENT = 4
	private val CUESHEET = 5
	private val PICTURE = 6

	private val BLOCK_SIZE = 4096
	private val minBlockSize = BLOCK_SIZE - 1
	private val maxBlockSize = BLOCK_SIZE - 1
	private val UNKNOWN_FRAME_SIZE = 0

	private val metadataBlockLength = 34
	private val numSamples = 0

	fun encode(output: BitOutputStream) {
		writeStreamMarker(output, fLaC)
		writeMetadata(output)
		writeFrames(output)
	}

	private fun writeMetadata(output: BitOutputStream) {
		println("----Begin Metadata---")
		writeLastMetadataBlockFlag(output, true)
		writeBlockType(output, STREAMINFO)
		writeMetadataBlockLength(output, metadataBlockLength)
		writeMinBlockSize(output, minBlockSize)
		writeMaxBlockSize(output, maxBlockSize)
		writeMinFrameSize(output, UNKNOWN_FRAME_SIZE)
		writeMaxFrameSize(output, UNKNOWN_FRAME_SIZE)
		writeSampleRate(output, sampleRate)
		writeNumChannels(output, numChannels)
		writeBitsPerSample(output, sampleDepth)
		writeNumStreamSamples(output, numSamples)
		writeEmptyMD5(output)
		println("----End Metadata---\n")
	}

	private fun writeStreamMarker(output: BitOutputStream, streamMarker: Int) {
		println("----Begin Stream Marker---")
		output.writeInt(32, streamMarker)
		println("----End Stream Marker---\n")
	}

	private fun writeNumStreamSamples(output: BitOutputStream, numSamples: Int) {
		println("----Begin Stream Samples---")
		output.writeInt(36, numSamples)
		println("----End Stream Samples---\n")
	}

	private fun writeEmptyMD5(output: BitOutputStream) {
		println("----Begin MD5---")
		for (i in 0..15) writeEmptyByte(output)
		println("----End MD5---\n")
	}

	private fun writeEmptyByte(output: BitOutputStream) {
		output.writeInt(8, 0)
	}

	private fun writeBitsPerSample(output: BitOutputStream, sampleDepth: Int) {
		println("----Begin Bits per Sample---")
		output.writeInt(5, sampleDepth - 1)
		println("----End Bits per Sample---\n")
	}

	private fun writeNumChannels(output: BitOutputStream, numChannels: Int) {
		println("----Begin Number Channels---")
		output.writeInt(3, numChannels - 1)
		println("----End Number Channels---\n")
	}

	private fun writeSampleRate(output: BitOutputStream, sampleRate: Int) {
		println("----Begin Sample Rate---")
		output.writeInt(20, sampleRate)
		println("----End Sample Rate---\n")
	}

	private fun writeMinBlockSize(output: BitOutputStream, minBlockSize: Int) {
		println("----Begin Min Block Size---")
		output.writeInt(16, minBlockSize)
		println("----End Min Block Size---\n")
	}

	private fun writeMaxBlockSize(output: BitOutputStream, maxBlockSize: Int) {
		println("----Begin Max Block Size---")
		output.writeInt(16, maxBlockSize)
		println("----End Max Block Size---\n")
	}

	private fun writeMetadataBlockLength(output: BitOutputStream, metadataBlockLength: Int) {
		println("----Begin Metadata Block Length---")
		output.writeInt(24, metadataBlockLength)
		println("----End Metadata Block Length---\n")
	}

	private fun writeMinFrameSize(output: BitOutputStream, FRAME_SIZE_UNKNOWN: Int) {
		println("----Begin Min Frame Size---")
		output.writeInt(24, FRAME_SIZE_UNKNOWN)
		println("----End Min Frame Size---\n")
	}

	private fun writeMaxFrameSize(output: BitOutputStream, FRAME_SIZE_UNKNOWN: Int) {
		println("----Begin Max Frame Size---")
		output.writeInt(24, FRAME_SIZE_UNKNOWN)
		println("----End Max Frame Size---\n")
	}

	private fun writeBlockType(output: BitOutputStream, STREAMINFO: Int) {
		println("----Begin Block Type---")
		output.writeInt(7, STREAMINFO)
		println("----End Block Type---\n")
	}

	private fun writeLastMetadataBlockFlag(output: BitOutputStream, isLast: Boolean) {
		println("----Begin Last Metadata Block Flag---")
		if (isLast) output.writeInt(1, 1)
		else output.writeInt(1, 0)
		println("----End Last Metadata Block Flag---\n")
	}

	private fun writeFrames(output: BitOutputStream) {
		println("----Begin Frames---")
		println("----End Frames---\n")
	}
}