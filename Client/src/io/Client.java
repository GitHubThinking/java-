/***
 * 
 * TestClient.java
 * @author Ashley Xu
 * 2016.8.28
 * ����һ���û��࣬��ֱ�Ӵ��Ͷ���
 *
 */

package io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import util.Message;

public class Client {
	/*
	 * public static void main(String[] args) throws ClassNotFoundException { Person
	 * person = new Person(); new TestClient().start(1101, person); }
	 */
	@SuppressWarnings("finally")
	public Message start(Message clientobj) throws ClassNotFoundException {
		Object obj = null;
		try {
			Socket socket = new Socket(clientobj.getIP(), 7777);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());// �����������������������
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));// ���������������շ������˷��͵���Ϣ

			// �������, һ��Ҫflush����
			oos.writeObject((Message) clientobj);// ��������Ѷ��󴫵ݵ�������
			oos.flush();
			obj = ois.readObject();// ��ȡ�����������յ� ����

			ois.close();
			oos.close();
			socket.close();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Error:����ʧ�ܣ���鿴�����Ƿ�������");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error:����ʧ�ܣ���鿴�����Ƿ�������");
			e.printStackTrace();
		} finally {
			return (Message) obj;
		}
	}
}
