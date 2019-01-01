package com.ddou.example.javaagent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class AopAgentExample {
	static private Instrumentation _inst = null;

	/**
	 * The agent class must implement a public static premain method similar in
	 * principle to the main application entry point. After the Java Virtual Machine
	 * (JVM) has initialized, each premain method will be called in the order the
	 * agents were specified, then the real application main method will be called.
	 **/
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("AopAgentExample.premain() was called.");

		/*
		 * Provides services that allow Java programming language agents to instrument
		 * programs running on the JVM.
		 */
		_inst = inst;

		/*
		 * ClassFileTransformer : An agent provides an implementation of this interface
		 * in order to transform class files.
		 */
		ClassFileTransformer trans = new AopAgentTransformer();

		System.out.println("Adding a AopAgentExample instance to the JVM.");

		/* Registers the supplied transformer. */
		_inst.addTransformer(trans);
	}
}
