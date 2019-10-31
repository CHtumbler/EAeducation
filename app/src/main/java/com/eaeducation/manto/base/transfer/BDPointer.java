package com.eaeducation.manto.base.transfer;

public class BDPointer {

    public static WGSPointer bd2wgs(double lat, double lng) {
        GCJPointer gcj = bd2gcj(lat, lng);
        return gcj.toWGSPointer();
    }

    private static GCJPointer bd2gcj(double bdLat, double bdLng) {
        double x = bdLng - 0.0065, y = bdLat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * Math.PI);
        double gcjLng = z * Math.cos(theta);
        double gcjLat = z * Math.sin(theta);
        return new GCJPointer(gcjLat, gcjLng);
    }
}
