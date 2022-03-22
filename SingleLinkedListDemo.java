package com.yve.list;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode();
        hero1.setName("宋江");
        hero1.setNickName("及时雨");
        hero1.setNum(1);
        HeroNode hero2 = new HeroNode("吴用",2,"智多星");
        HeroNode hero3 = new HeroNode("卢俊义",3,"玉麒麟");
        HeroNode hero4 = new HeroNode("林冲",4,"豹子头");
        HeroNode hero37 = new HeroNode("朱武",37,"神机军师");
        SingleLinkedList list = new SingleLinkedList();
        list.orderAdd2(hero4);
        list.orderAdd2(hero3);
        list.orderAdd2(hero37);
        list.orderAdd2(hero2);
        list.orderAdd2(hero1);
        list.update(new HeroNode("lu",3,"qiling"));
        list.list();
        System.out.println("=================");
        //list.reversal();
        list.reverseList();
    }
}

class SingleLinkedList {
    private HeroNode head;
    private HeroNode temp;

    public SingleLinkedList() {
        head = new HeroNode();
    }

    /**
     * Add a new hero at the end
     * @param hero  new hero
     */
    public void add(HeroNode hero) {
        if (isEmpty()) {
            head.setNext(hero);
        }else {
            last().setNext(hero);
        }
    }

    /**
     * Add hero by num order(By myself)
     * @param hero new hero
     */
    public void orderAdd(HeroNode hero) {
        HeroNode index = new HeroNode();
        temp = head;
        while (temp!= null){
            if (temp.getNum() < hero.getNum()) {
                index = temp;
                temp = temp.getNext();
            } else if (temp.getNum() > hero.getNum()) {
                index.setNext(hero);
                hero.setNext(temp);
                return;
            } else {
                    System.out.println("This num exist");
                    return;
            }
        }
        index.setNext(hero);
    }

    /**
     * Add hero by num order(online version)
     * @param hero new hero
     */
    public void orderAdd2(HeroNode hero) {
        temp = head;
        while (temp.getNext()!= null){
            if (temp.getNext().getNum() < hero.getNum()) {
                temp = temp.getNext();
            } else if (temp.getNext().getNum() > hero.getNum()) {
                hero.setNext(temp.getNext());
                temp.setNext(hero);
                return;
            } else {
                System.out.println("num "+hero.getNum()+" exist");
                return;
            }
        }
        temp.setNext(hero);
    }

    /**
     * update hero entities
     * @param hero new hero to recover
     */
    public void update(HeroNode hero) {
        HeroNode original = new HeroNode();
        if (find(hero.getNum()) == null) {
            System.out.println("This hero is not exist");
        }
        original = find(hero.getNum());
        original.setName(hero.getName());
        original.setNickName(hero.getNickName());
    }

    /**
     * remove hero by no
     * @param no no
     */
    public void remove(int no) {
        temp = findPrivate(no);
        temp.setNext(temp.getNext().getNext());
    }

    /**
     * find hero by no
     * @param no no
     * @return the hero found
     */
    public HeroNode find(int no) {
        temp = head;
        while (temp.getNext() != null) {
            if (temp.getNum() == no) {
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }

    /**
     * find private hero by no
     * @param no
     * @return the private hero
     */
    public HeroNode findPrivate(int no) {
        if (isEmpty()) {
            System.out.println("This queue is empty");
            return head;
        }
        temp = head;
        while (temp.getNext() != null) {
            if (temp.getNext().getNum() == no) {
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }

    /**
     * find k from bottom
     * @param k num from bottom
     * @return return kth hero from bottom
     */
    public HeroNode findK(int k) {
        int index = numOfHero() - k;
        temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.getNext();
        }
        if (temp == head) {
            System.out.println("此节点不存在");
            return null;
        }
        return temp;
    }

    /**
     * reverse single linked list
     */
    public void reversal() {
        if (isEmpty() || head.getNext().getNext() == null) {
            return;
        }
        HeroNode cur = head.getNext();
        HeroNode next = null;
        HeroNode reversalHead = new HeroNode();
        while (cur != null) {
            next = cur.getNext();
            cur.setNext(reversalHead.getNext());
            reversalHead.setNext(cur);
            cur = next;
        }
        head.setNext(reversalHead.getNext());
    }

    /**
     * find the last hero
     * @return the last hero
     */
    public HeroNode last() {
        if (isEmpty()) {
            System.out.println("This linked list is empty");
            return null;
        }
        temp = head.getNext();
        while (true) {
            if (temp.getNext() != null) {
                temp = temp.getNext();
            }else {
                return temp;
            }
        }
    }

    public int numOfHero() {
        int num = 0;
        temp = head;
        while (temp.getNext() != null) {
                num++;
                temp = temp.getNext();
        }
        return num;
    }


    /**
     * list all heroes
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("This linked list is empty");
            return;
        }
        temp = head.getNext();
        while (true) {
            System.out.println(temp);
            temp = temp.getNext();
            if (temp.getNext() == null) {
                System.out.println(temp);
                return;
            }
        }
    }

    public void reverseList() {
        Stack stack = new Stack();
        temp = head.getNext();
        while (temp != null) {
            stack.push(temp);
            temp = temp.getNext();
        }
        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }

    /**
     * check if the linked list is empty
     * @return true: this linked list is empty; false: not empty
     */
    public boolean isEmpty() {
        return head.getNext() == null;
    }
}


class HeroNode{
    private String name;
    private int num;
    private String nickName;
    private HeroNode next;

    public HeroNode(String name, int num, String nickName) {
        this.name = name;
        this.num = num;
        this.nickName = nickName;
    }

    public HeroNode() {
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public int getNum() {
        return num;
    }

    public String toString(){
        return "name:" + name + " num:" + num + " nickname:" + nickName;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void setNext(HeroNode next) {
     this.next = next;
    }

    public HeroNode getNext() {
        return next;
    }
}
