/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;

public abstract class CommonFilterImpl implements CommonFilter {
	protected ArrayList<PipedInputStream> vIn = new ArrayList<PipedInputStream>();
	protected ArrayList<PipedOutputStream> vOut = new ArrayList<PipedOutputStream>();
	private int portCount;
	
	public CommonFilterImpl(int portCount) {
		this.portCount = portCount;
		initPipedInOutStream();
	}
	private void initPipedInOutStream() {
		for(int i=0; i<portCount; i++) {
			vIn.add(new PipedInputStream());
			vOut.add(new PipedOutputStream());
		}
	}
	public void connectOutputTo(int pipeNum, CommonFilter nextFilter) throws IOException {
		vOut.get(pipeNum).connect(nextFilter.getPipedInputStream().get(pipeNum));
	}
	public void connectInputTo(int pipeNum, CommonFilter previousFilter) throws IOException {
		vIn.get(pipeNum).connect(previousFilter.getPipedOutputStream().get(pipeNum));
	}
	public ArrayList<PipedInputStream> getPipedInputStream() {
		return vIn;
	}
	public ArrayList<PipedOutputStream> getPipedOutputStream() {
		return vOut;
	}
	
	abstract public boolean specificComputationForFilter() throws IOException;
	// Implementation defined in Runnable interface for thread
	public void run() {
		try {
			specificComputationForFilter();
		} catch (IOException e) {
			if (e instanceof EOFException) return;
			else System.out.println(e);
		} finally {
			closePorts();
		}
	}
	private void closePorts() {
		try {
			for(int i=0; i<portCount; i++) {
				vOut.get(i).close();
				vIn.get(i).close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
