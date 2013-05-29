package pippin;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

/* READ THE ASSIGNMENT CAREFULLY!! The following skeleton
 * was created simply by reading the assignment. Most, if
 * not all the information that you need is there.
 * 
 * Take an input source
 * file. Read it line by line. For each line, make sure
 * the line has a legal format. If it does, convert it
 * to a long, otherwise throw an exception.
 */


public class Assembler
{
	public static Map<String, Integer> opcodes = new HashMap<String, Integer>();
	public static Map<String, Boolean> requiresArgument = new HashMap<String, Boolean>();

	static{
		opcodes.put("NOP",  0x00);	requiresArgument.put("NOP",  false);
		opcodes.put("LOD",  0x01);	requiresArgument.put("LOD",  true);
		opcodes.put("LODI", 0x02);	requiresArgument.put("LODI", true);
		opcodes.put("STO",  0x03);	requiresArgument.put("STO",  true);
		opcodes.put("ADD",  0x04);	requiresArgument.put("ADD",  true);
		opcodes.put("SUB",  0x05);	requiresArgument.put("SUB",  true);
		opcodes.put("MUL",  0x06);	requiresArgument.put("MUL",  true);
		opcodes.put("DIV",  0x07);	requiresArgument.put("DIV",  true);
		opcodes.put("ADDI", 0x08);	requiresArgument.put("ADDI", true);
		opcodes.put("SUBI", 0x09);	requiresArgument.put("SUBI", true);
		opcodes.put("MULI", 0x0A);	requiresArgument.put("MULI", true);
		opcodes.put("DIVI", 0x0B);	requiresArgument.put("DIVI", true);
		opcodes.put("AND",  0x10);	requiresArgument.put("AND",  true);
		opcodes.put("ANDI", 0x11);	requiresArgument.put("ANDI", true);
		opcodes.put("NOT",  0x12);	requiresArgument.put("NOT",  false);
		opcodes.put("CMPZ", 0x13);	requiresArgument.put("CMPZ", true);
		opcodes.put("CMPL", 0x14);	requiresArgument.put("CMPL", true);
		opcodes.put("JUMP", 0x1A);	requiresArgument.put("JUMP", true);
		opcodes.put("JMPZ", 0x1B);	requiresArgument.put("JMPZ", true);
		opcodes.put("HALT", 0x1F);	requiresArgument.put("HALT", false);
	}

	public static boolean assembleFile(File inFile, File outFile)
	{
		/* Open the file into a Scanner
		 * http://docs.oracle.com/javase/1.5.0/docs/api/java/util/Scanner.html
		 * Look at the API for scanner for details
		 */
		Scanner input = new Scanner(System.in);
		try
		{
			input = new Scanner(inFile);
		} catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boolean goodProgram = true;
		boolean blankLineHit = false;
		ArrayList<Long> codeLines = new ArrayList<Long>(); 
		ArrayList<Long> dataLines = new ArrayList<Long>(); 
		int lineCounter = 0;
		while( input.hasNextLine() && goodProgram)
		{
			String line = input.nextLine().toUpperCase();
			System.out.println(line);
			lineCounter++;
			if(blankLineHit && line.trim().length() > 0)
			{
				goodProgram = false;
				System.out.println("Blank Lines After Code.   Line: "+lineCounter);
			}
			else if(!blankLineHit && line.trim().length() == 0)
				blankLineHit = true;
			//			else if(!blankLineHit && line.trim().length() > 0)
			//			{
			//				long writeMe = 0;	
			//				if( line.charAt(0) == ' ' || line.charAt(0) == '\t' )//is ' ' or '\t' change goodProgram 
			//				{
			//					goodProgram = false;
			//					System.out.println("Illegal White Space.   Line: "+lineCounter);
			//			}
			else if(goodProgram)				
			{		//split the line by any white space:
				long writeMe = 0;	
				String[ ] parts = line.split("\\s+"); 
				if( parts.length > 2)
				{
					goodProgram = false; 
					System.out.println("There is an illegal instruction or memory assignment.   Line: "+lineCounter);
				}
				else{
					if(opcodes.keySet().contains(parts[0]))
					{
						if( requiresArgument.get(parts[0]) && parts.length == 2) 
						{
							try {
								boolean indirect = false;
								if(parts[1].charAt(0) == 'N')
								{
									indirect = true;
									parts[1] = parts[1].substring(1);
									System.out.println(parts[0]+" "+parts[1]);
								}
								int arg = Integer.parseInt(parts[1], 16);

								int op = opcodes.get(parts[0]);
								//int op = Integer.parseInt(opcode, 16);

								long data= (( ((long)op) << 32) + arg);
								if(indirect)
									data *= -1L;
								writeMe = data;
								/* SEE ASSIGNMENT FOR CONVERTING
								 * INSTRUCTION AND ARG TO PROPER
								 * FORMAT.
								 */
								codeLines.add(writeMe);
							}
							catch(NumberFormatException e) {
								goodProgram  = false;
								JOptionPane.showMessageDialog(null,
										e.getMessage(),
										"Assembling Code Failure", JOptionPane.WARNING_MESSAGE);
							}
						}
						else if(!requiresArgument.get(parts[0]) && parts.length == 1){


							int op = opcodes.get(parts[0]);
							long data= ((((long)op) << 32));
							writeMe = data;
							/* SEE ASSIGNMENT FOR CONVERTING
							 * INSTRUCTION AND ARG = 0 TO PROPER
							 * FORMAT.

								IMPORTANT x<<32+y MEANS x<<(32+y) SO
								YOU NEED (x<<32)+y

							 */
							codeLines.add(writeMe);
						}
					}
					else if(!opcodes.keySet().contains(parts[0]))
					{
						if(parts.length == 2)
						{
							try
							{
								if(parts[0].matches("[01234456789ABCDEF]+") && parts[1].matches("[01234456789ABCDEF]+"))
								{
									long oper = Integer.parseInt(parts[0],16);
									long arg = Integer.parseInt(parts[1],16);
									writeMe = ((long)oper<<32) + arg;
									dataLines.add(writeMe);
								}
							}catch(Exception e)
							{
								JOptionPane.showMessageDialog(null,
										e.getMessage(),
										"Assembling Data Failure", JOptionPane.WARNING_MESSAGE);
							}
						}
					}
					else{
						goodProgram = false;
						System.out.println("Illegal Instruction");
					}
				}
				if(goodProgram)
				{
					try{
						FileOutputStream fos = new FileOutputStream(outFile);
						DataOutputStream dos = new DataOutputStream(fos);
						for(long num : codeLines) {
							dos.writeLong(num);
						}
						dos.writeLong(-1L);
						for(long num : dataLines) {
							dos.writeLong(num);
						}
						dos.close();
					} catch (IOException e){
						System.out.println("IOException : " + e);
					}
				}
			}
		}
		return goodProgram;
	}
}



