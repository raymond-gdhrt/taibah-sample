/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pahappa.systems.constants;

/**
 *
 * @author RayGdhrt
 */
public enum MaritalStatus {
    SINGLE("Single"),
    MARRIED("Married"),
    COMPLICATED("Rather not say");
    public String uiName;

    private MaritalStatus(String name) {
        this.uiName = name;
    }

}
