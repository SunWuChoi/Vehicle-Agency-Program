//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package transaction;

// @author Sun Wu Choi

import java.util.ArrayList;

 
public class Transactions {
    private ArrayList<Transaction> transactionList;

    public Transactions() {
        this.transactionList = new ArrayList<Transaction>();
    }
    
    public Transactions(Transaction[] list) {
        this.transactionList = new ArrayList<Transaction>();
        for(int i = 0; i < list.length; i++){
            transactionList.add(list[i]);
        }
    }
    
    public void add(Transaction transaction){
        transactionList.add(transaction);
    }
    
    public Transaction getTransaction(String acctNum){
        int size = transactionList.size();
        
        for (int i = 0; i < size; i++) {
            if(transactionList.get(i).getAcctNum().equals(acctNum)){
                return transactionList.get(i);
            }
        }
        return null;
    }
    
    public int getSize(){
        return transactionList.size();
    } 
    
    public Transaction getTransaction(int i){
        return transactionList.get(i);
    }
}
