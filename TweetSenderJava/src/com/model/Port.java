/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.Objects;

/**
 *
 * @author henriquevalcanaia
 */
public class Port {

    private String portName;
    private int portType;

    public Port() {
    }

    public Port(String portName, int portType) {
        this.portName = portName;
        this.portType = portType;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public int getPortType() {
        return portType;
    }

    public void setPortType(int portType) {
        this.portType = portType;
    }

    @Override
    public String toString() {
        return "Port{" + "portName=" + portName + ", portType=" + portType + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.portName);
        hash = 53 * hash + Objects.hashCode(this.portType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Port other = (Port) obj;
        if (!Objects.equals(this.portName, other.portName)) {
            return false;
        }
        if (!Objects.equals(this.portType, other.portType)) {
            return false;
        }
        return true;
    }
}
