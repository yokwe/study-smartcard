package mh.smartcard.yubico;

import java.util.ArrayList;
import java.util.List;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Hex;
import mh.smartcard.util.ByteBuffer;
import mh.smartcard.util.Util;

public class Status {
	public final int versionMajor;
	public final int versionMinor;
	public final int versionBuild;
	public final int pgmSeq;
	public final int touchLevel;
	
	public Status(byte[] bytes) {
		ByteBuffer bb = new ByteBuffer(bytes);
		
		versionMajor = bb.get();
		versionMinor = bb.get();
		versionBuild = bb.get();
		pgmSeq       = bb.get();
		touchLevel   = bb.getShort();
		
		// Sanity check
		if (bb.hasRemaining()) {
			throw new UnexpectedException(String.format("pos = %d  bytes = (%d)%s", bb.position(), bytes.length, Hex.toString(bytes)));
		}
	}
	
	@Override
	public String toString() {
		List<String> elements = new ArrayList<>();
		
		elements.add(String.format("versionMajor %d", versionMajor));
		elements.add(String.format("versionMinor %d", versionMinor));
		elements.add(String.format("versionBuild %d", versionBuild));
		elements.add(String.format("pqmSeq %d",       pgmSeq));
		elements.add(String.format("touchLevel %d",   touchLevel));
		
		return Util.join(elements);
	}
}
