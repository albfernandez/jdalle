package net.sf.dalle.ui.cli;

import org.junit.Assert;
import org.junit.Test;

public class FileSizeParserTest {


	@Test
	public void testToBytes1 () {
		Assert.assertEquals(1024L, FileSizeParser.toBytes("1KB"));
		Assert.assertEquals(1024L, FileSizeParser.toBytes("1kb"));
		Assert.assertEquals(1024L, FileSizeParser.toBytes("1Kb"));
		Assert.assertEquals(1024L, FileSizeParser.toBytes("1kB"));
		Assert.assertEquals(1024L*1024L, FileSizeParser.toBytes("1MB"));
		Assert.assertEquals(1024L*1024L*1024L, FileSizeParser.toBytes("1GB"));
		Assert.assertEquals(1024L*1024L*1024L*1024L, FileSizeParser.toBytes("1TB"));
	}
}
