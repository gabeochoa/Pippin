package pippin;

import java.util.Arrays;

public class Memory {
    public static final int DATA_SIZE = 256;
    // this needs more work for the whole Pippin project
    private int[] data = new int[DATA_SIZE];
    

    public static final int CODE_SIZE = 256;
    private CodeLine[ ] code = new CodeLine[CODE_SIZE];
    
    
    public void setData(int index, int value)throws DataAccessException {
        if(index < 0 || index >= data.length) {
            throw new DataAccessException("ERROR: Cannot access data location " + index);
        }
        data[index] = value;
    }
    
    public int getData(int index) throws DataAccessException {
        if(index < 0 || index >= data.length) {
            throw new DataAccessException("ERROR: Cannot access data location " + index);
        }
        return data[index];
    }
    
 // Note package private -- just for JUnit test
 	CodeLine[] getCode() {
 		return code;
 	}

 	// Note package private -- just for JUnit test
 	int[] getData() {
 		return data;
 	}
 	
 	// Note package private -- just for debugging
 	void printCode() {
 		System.out.println(Arrays.toString(code));
 	}
 		
 	// Note package private -- just for debugging
 	void printData() {
 		System.out.println(Arrays.toString(data));
 	}
    
    public void setCode(int index, CodeLine value) throws CodeAccessException
    {
    	code[index] = value;
    }
    public CodeLine getCode(int index) throws CodeAccessException
    {
		return code[index];
    }
    
    public void clearCode() {
        Arrays.fill(code, null);    
    }
    public void clearData() {
        Arrays.fill(data, 0);        
    }

        
}