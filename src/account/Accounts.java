//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package account;

// @author Sun Wu Choi

import java.util.LinkedList;

 
public class Accounts {
    LinkedList<Account> accountList;    // used LinkedList because account might be removed
                                        // if the program gets a menu "remove account"
    public Accounts() {
        this.accountList = new LinkedList<Account>();
    }
    
    public Accounts(Account[] list){
        this.accountList = new LinkedList<Account>();
        for(int i = 0; i < list.length; i++){
            accountList.add(list[i]);
        }  
    }
    
    public void add(Account account){
        accountList.add(account);
    }
    
    public boolean remove(String acctNum){
        int size = accountList.size();
        for(int i = 0; i < size; i++){
            if(accountList.get(i).getAcctNum().equals(acctNum)){
                System.out.println("Account of " + accountList.get(i).getCompany() + " with account number " + accountList.get(i).getAcctNum() + " Removed");
                accountList.remove(i);
                return true;
            }
        }
        System.out.println("No such account with account number " + acctNum + " found");
        return false;
    }
    
    public Account getAccount(String acctNum){
        int size = accountList.size();
        
        for(int i = 0; i < size; i++){
            if(accountList.get(i).getAcctNum().equals(acctNum)){
                return accountList.get(i);
            }
        }
        return null;
    }
    
    public int getSize(){
        return accountList.size();
    }
    
    public Account getAccount(int i){
        return accountList.get(i);
    }
    
}
