package com.s4ng.jobbylanguage.model.dto

data class SingleFlowData(val label: String, val data: MutableList<Int>, var backgroundColor: String?, var borderColor: String?) {
    fun setColor(color: String) {
        this.backgroundColor = color;
        this.borderColor = color;
    }
}
