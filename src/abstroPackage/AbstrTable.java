/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstroPackage;

import enums.Enum;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author ludek
 */
public class AbstrTable<K extends Comparable<K>, V> implements IAbstrTable<K, V>{

    private class Node{
        
        V value;
        K key;
        Node right;
        Node left;

        public Node(K key, V value) {
            this.value = value;
            this.key = key;
            this.right = right;
            this.left = left;
        }
        
    }
    
    private Node root;
    
    @Override
    public void zrus() {
        
        root = null;
        
    }

    @Override
    public boolean jePrazdny() {return root == null;};

    @Override
    public V najdi(K key) {
        
        Node hledanyPrvek = root;
        
        while(true){
            if(key.compareTo(hledanyPrvek.key) == 0){
                return hledanyPrvek.value;
            }else if(key.compareTo(hledanyPrvek.key) == 1){
                hledanyPrvek = hledanyPrvek.right;
            }else if(key.compareTo(hledanyPrvek.key) == -1){
                hledanyPrvek = hledanyPrvek.left;
            }else{
                return null;
            }
        }
        
    }

    @Override
    public void vloz(K key, V value) {
        
        Node novy = new Node(key, value);
        
        if(root == null){
            root = novy;
        }else{
            Node predchudce;
            
            Node aktualni = root;
            
            while(true){
                
                predchudce = aktualni;
                
                if(key.compareTo(aktualni.key) < 0){
                    
                    aktualni = aktualni.left;
                    
                    if(aktualni == null){
                        predchudce.left = novy;
                        return;
                    }
                    
                }else{
                    
                    aktualni = aktualni.right;
                    
                    if(aktualni == null){
                        
                        predchudce.right = novy;
                        return;
                        
                    }
                    
                }
                
                
            }
        }
        
    }

    @Override
    public V odeber(K key) {
        Node odebranyPrvek = root;
        Node parent = root;
        
        if(jePrazdny()){
            return null;
        }
        if(key.compareTo(root.key) == 0){
            if(parent.left == null && parent.right == null){
                zrus();
            }
            if(parent.left == null){
                root = parent.right;
            }else if(parent.right == null){
                root = parent.left;
            }else{
                Node levyPrvek = root.left;
                Node pravyPrvek = root.right;
                root = nejblizsiPrvek(parent);
                root.left = levyPrvek;
                root.right = pravyPrvek;
            }
            return odebranyPrvek.value;
        }
        while(true){
            if(key.compareTo(odebranyPrvek.key) == 0){
                if(key.compareTo(parent.key) == 1){
                    if(odebranyPrvek.right == null && odebranyPrvek.left == null){
                        parent.right = null;
                    }else if(odebranyPrvek.right == null){
                        parent.right = odebranyPrvek.left;
                    }else if(odebranyPrvek.left == null){
                        parent.right = odebranyPrvek.right;
                    }else{
                        Node nejblizsi = nejblizsiPrvek(odebranyPrvek);
                        parent.right = nejblizsi;
                        Node novyPrvek = parent.right;
                        Node levyPrvek = odebranyPrvek.left;
                        novyPrvek.left = levyPrvek;
                        novyPrvek.right = odebranyPrvek.right;
                    }
                }else{
                    if(odebranyPrvek.right == null && odebranyPrvek.left == null){
                        parent.left = null;
                    }else if(odebranyPrvek.right == null){
                        parent.left = odebranyPrvek.left;
                    }else if(odebranyPrvek.left == null){
                        parent.left = odebranyPrvek.right;
                    }else{
                        Node nejblizsi = nejblizsiPrvek(odebranyPrvek);
                        parent.left = nejblizsi;
                        Node novyPrvek = parent.left;
                        Node levyPrvek = odebranyPrvek.left;
                        novyPrvek.left = levyPrvek;
                        novyPrvek.right = odebranyPrvek.right;
                    }
                }
                break;
            }else if(key.compareTo(odebranyPrvek.key) == 1){
                
                parent = odebranyPrvek;
                odebranyPrvek = odebranyPrvek.right;
                
            }else{
                
                parent = odebranyPrvek;
                odebranyPrvek = odebranyPrvek.left;
                
            }
        }
        
        
        return odebranyPrvek.value;
    }
    
    private Node nejblizsiPrvek(Node odPrvku){
        Node parent = odPrvku;
        Node nejblizsi = odPrvku.right;
        while(true){
            if(nejblizsi.left != null){
                parent = nejblizsi;
                nejblizsi = nejblizsi.left;
            }else{
                break;
            }
        }
        if(nejblizsi.right != null && parent.right != nejblizsi){
            parent.left = nejblizsi.right;
        }else{
            parent.left = null;
        }
        
        return nejblizsi;
    }

    @Override
    public Iterator iterator(Enum.eTypProhl typ) {
        Iterator zvolenyIterator = null;
        switch(typ){
            case HLOUBKY:
                zvolenyIterator = new Iterator() {

                    private IAbstrLifo zasobnik = new AbstrLifo();
                    
                    private boolean inicializace = false;
                    
                    @Override
                    public boolean hasNext() {
                        if(!inicializace){
                            zasobnik.vlozData(root);
                            inicializace = true;
                        }
                        return !zasobnik.jePrazdny();
                    }

                    @Override
                    public V next() {
                        Node pomocna = (Node) zasobnik.odeber();
                        if(pomocna.left != null){
                            zasobnik.vlozData(pomocna.left);
                        }
                        if(pomocna.right != null){
                            zasobnik.vlozData(pomocna.right);
                        }
                        return pomocna.value;
                    }

                };
                break;
            case SIRKY:
                zvolenyIterator = new Iterator() {

                    private IAbstrLifo zasobnik = new AbstrFifo();
                    
                    private boolean inicializace = false;
                    
                    @Override
                    public boolean hasNext() {
                        if(!inicializace){
                            nactiLevo(root);
                            inicializace = true;
                        }
                        return !zasobnik.jePrazdny();
                    }

                    @Override
                    public V next() {
                        Node pomocna = (Node) zasobnik.odeber();
                        nactiLevo(pomocna.right);
                        return pomocna.value;
                    }

                    private void nactiLevo(Node node){
                        while(node != null){
                            zasobnik.vlozData(node);
                            node = node.left;
                        }
                    }
                };
                break;
        }
        return zvolenyIterator;
    }
    
    private Node createBalancedTreeHelp(ArrayList<K> listK, ArrayList<V> listV, int start, int end){
        
        if(start>end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(listK.get(mid), listV.get(mid));
        
        node.left = createBalancedTreeHelp(listK, listV, start, mid -1);
        node.right = createBalancedTreeHelp(listK, listV, mid + 1, end);
        
        return node;
        
    }
    
    public void createBalancedTree(ArrayList<K> listK, ArrayList<V> listV, int start, int end){
        root = createBalancedTreeHelp(listK, listV, start, end);
    }
}
