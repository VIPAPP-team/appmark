package com.vipapp.appmark2.xml;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vipapp.appmark2.callback.Mapper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("WeakerAccess")
public class XMLArray implements Iterable<XMLObject>{

    private Document source;
    private NodeList list;
    private Node parent;

    XMLArray(NodeList list, Node parent, Document source){
        this.list = list;
        this.parent = parent;
        this.source = source;
    }

    public int getLength(){
        return list.getLength();
    }
    public XMLObject getAtPosition(int pos){
        return new XMLObject(list.item(pos), source);
    }
    @Nullable
    public XMLObject getWithFilter(Mapper<XMLObject, Boolean> filter){
        for(int i = 0; i < list.getLength(); i++){
            XMLObject object = new XMLObject(list.item(i), source);
            if(filter.map(object)) return object;
        }
        return null;
    }

    public void add(XMLObject object){
        parent.appendChild(source.importNode(object.toNode(), true));
        list = ((Element)parent).getElementsByTagName("string");
    }

    public ArrayList<XMLObject> toList(){
        ArrayList<XMLObject> returnable = new ArrayList<>();
        for(int i = 0; i < getLength(); i++){
            returnable.add(getAtPosition(i));
        }
        return returnable;
    }

    @NonNull
    public Iterator<XMLObject> iterator() {
        return new Iterator<XMLObject>() {
            int current = 0;
            @Override
            public boolean hasNext() {
                return current < list.getLength();
            }

            @Override
            public XMLObject next() {
                return getAtPosition(current++);
            }
        };
    }
}
