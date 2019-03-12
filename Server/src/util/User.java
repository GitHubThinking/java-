package util;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected int _EcardNumber;
    
    /** ��� ��1Ϊѧ����0Ϊ����Ա */
    protected int _identity;
    private String _password;
    
    public User() {
    	setEcardNumber(0);
    	setIdentity(1);
    	setPassword(null);
    }
    
    public User(int EcardNumber, int identity, String password) {
    	setEcardNumber(EcardNumber);
    	setIdentity(identity);
    	setPassword(password);
    }
    
    //setter
    public void setEcardNumber(int EcardNumber) {
    	this._EcardNumber = EcardNumber;
    }
    
    public void setIdentity(int identity) {
    	this._identity = identity;
    }
    
    public void setPassword(String password) {
    	this._password = password;
    }
    
    //getter
    public int getEcardNumber() {
    	return this._EcardNumber;
    }
    
    public int getIdentity() {
    	return this._identity;
    }
    
    public String getPassword() {
    	return this._password;
    }
}

