package com.company;

class Circle {
        private double radius;
        private double area;

        Circle (double radius) {
          this.radius=radius;
          this.area =Math.PI * radius * radius;
}

    void setRadius(double radius) {

        this.radius = radius;
        this.area = Math.PI * radius * radius;
    }

    double getRadius(){
            return this.radius;
    }

               String getInfo () {
               return "Circle (R=" + this.radius + ", A=" + this.area + ") ";

               }
}
