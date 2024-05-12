package symphys.symphys.fields.sim;

class Physics {
    static double G = 6.674301515151515e-11;
    static double k = 1/(4*Math.PI*8.8541878128131313e-12);

    static Wektor calcGravityForce(BodyState o1, BodyState o2) { // sila dzialajaca na o1
        double dist2 = Wektor.dist2(o1.position, o2.position);
        if (dist2==0) return new Wektor();
        double val = Physics.G*o1.body.mass*o2.body.mass/dist2;
        return (o2.position.sub(o1.position)).normalize().mul(val);
    }

    static Wektor calcCoulombForce(BodyState o1, BodyState o2) {
        double dist2 = Wektor.dist2(o1.position, o2.position);
        if (dist2==0) return new Wektor();
        double val = Physics.k*o1.body.charge*o2.body.charge/dist2;
        // System.out.println("DEBUG: val="+val+" dist2="+dist2);
        return (o2.position.sub(o1.position)).normalize().mul(-val);
    }
}
