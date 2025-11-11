package com.badlogic.gdx.math;

import com.badlogic.gdx.math.collision.BoundingBox;

public class Frustum {
    protected static final Vector3[] clipSpacePlanePoints;
    protected static final float[] clipSpacePlanePointsArray;
    public final Vector3[] planePoints;
    protected final float[] planePointsArray;
    public final Plane[] planes;
    private static final Vector3 tmpV;

    static {
        Vector3[] arr_vector3 = new Vector3[8];
        int v = 0;
        arr_vector3[0] = new Vector3(-1.0f, -1.0f, -1.0f);
        arr_vector3[1] = new Vector3(1.0f, -1.0f, -1.0f);
        arr_vector3[2] = new Vector3(1.0f, 1.0f, -1.0f);
        arr_vector3[3] = new Vector3(-1.0f, 1.0f, -1.0f);
        arr_vector3[4] = new Vector3(-1.0f, -1.0f, 1.0f);
        arr_vector3[5] = new Vector3(1.0f, -1.0f, 1.0f);
        arr_vector3[6] = new Vector3(1.0f, 1.0f, 1.0f);
        arr_vector3[7] = new Vector3(-1.0f, 1.0f, 1.0f);
        Frustum.clipSpacePlanePoints = arr_vector3;
        Frustum.clipSpacePlanePointsArray = new float[24];
        Vector3[] arr_vector31 = Frustum.clipSpacePlanePoints;
        for(int v1 = 0; v < arr_vector31.length; v1 += 3) {
            Vector3 vector30 = arr_vector31[v];
            Frustum.clipSpacePlanePointsArray[v1] = vector30.x;
            Frustum.clipSpacePlanePointsArray[v1 + 1] = vector30.y;
            Frustum.clipSpacePlanePointsArray[v1 + 2] = vector30.z;
            ++v;
        }
        Frustum.tmpV = new Vector3();
    }

    public Frustum() {
        this.planes = new Plane[6];
        this.planePoints = new Vector3[]{new Vector3(), new Vector3(), new Vector3(), new Vector3(), new Vector3(), new Vector3(), new Vector3(), new Vector3()};
        this.planePointsArray = new float[24];
        for(int v = 0; v < 6; ++v) {
            this.planes[v] = new Plane(new Vector3(), 0.0f);
        }
    }

    public boolean boundsInFrustum(float f, float f1, float f2, float f3, float f4, float f5) {
        for(int v = 0; v < this.planes.length; ++v) {
            float f6 = f + f3;
            float f7 = f1 + f4;
            float f8 = f2 + f5;
            if(this.planes[v].testPoint(f6, f7, f8) == PlaneSide.Back) {
                float f9 = f2 - f5;
                if(this.planes[v].testPoint(f6, f7, f9) == PlaneSide.Back) {
                    float f10 = f1 - f4;
                    if(this.planes[v].testPoint(f6, f10, f8) == PlaneSide.Back && this.planes[v].testPoint(f6, f10, f9) == PlaneSide.Back) {
                        float f11 = f - f3;
                        if(this.planes[v].testPoint(f11, f7, f8) == PlaneSide.Back && this.planes[v].testPoint(f11, f7, f9) == PlaneSide.Back && this.planes[v].testPoint(f11, f10, f8) == PlaneSide.Back && this.planes[v].testPoint(f11, f10, f9) == PlaneSide.Back) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean boundsInFrustum(Vector3 vector30, Vector3 vector31) {
        return this.boundsInFrustum(vector30.x, vector30.y, vector30.z, vector31.x / 2.0f, vector31.y / 2.0f, vector31.z / 2.0f);
    }

    public boolean boundsInFrustum(BoundingBox boundingBox0) {
        int v = 0;
        while(v < this.planes.length) {
            if(this.planes[v].testPoint(boundingBox0.getCorner000(Frustum.tmpV)) != PlaneSide.Back || this.planes[v].testPoint(boundingBox0.getCorner001(Frustum.tmpV)) != PlaneSide.Back || this.planes[v].testPoint(boundingBox0.getCorner010(Frustum.tmpV)) != PlaneSide.Back || this.planes[v].testPoint(boundingBox0.getCorner011(Frustum.tmpV)) != PlaneSide.Back || this.planes[v].testPoint(boundingBox0.getCorner100(Frustum.tmpV)) != PlaneSide.Back || this.planes[v].testPoint(boundingBox0.getCorner101(Frustum.tmpV)) != PlaneSide.Back || this.planes[v].testPoint(boundingBox0.getCorner110(Frustum.tmpV)) != PlaneSide.Back || this.planes[v].testPoint(boundingBox0.getCorner111(Frustum.tmpV)) != PlaneSide.Back) {
                ++v;
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean pointInFrustum(float f, float f1, float f2) {
        for(int v = 0; true; ++v) {
            Plane[] arr_plane = this.planes;
            if(v >= arr_plane.length) {
                break;
            }
            if(arr_plane[v].testPoint(f, f1, f2) == PlaneSide.Back) {
                return false;
            }
        }
        return true;
    }

    public boolean pointInFrustum(Vector3 vector30) {
        for(int v = 0; true; ++v) {
            Plane[] arr_plane = this.planes;
            if(v >= arr_plane.length) {
                break;
            }
            if(arr_plane[v].testPoint(vector30) == PlaneSide.Back) {
                return false;
            }
        }
        return true;
    }

    public boolean sphereInFrustum(float f, float f1, float f2, float f3) {
        for(int v = 0; v < 6; ++v) {
            if(this.planes[v].normal.x * f + this.planes[v].normal.y * f1 + this.planes[v].normal.z * f2 < -f3 - this.planes[v].d) {
                return false;
            }
        }
        return true;
    }

    public boolean sphereInFrustum(Vector3 vector30, float f) {
        for(int v = 0; v < 6; ++v) {
            if(this.planes[v].normal.x * vector30.x + this.planes[v].normal.y * vector30.y + this.planes[v].normal.z * vector30.z < -f - this.planes[v].d) {
                return false;
            }
        }
        return true;
    }

    public boolean sphereInFrustumWithoutNearFar(float f, float f1, float f2, float f3) {
        for(int v = 2; v < 6; ++v) {
            if(this.planes[v].normal.x * f + this.planes[v].normal.y * f1 + this.planes[v].normal.z * f2 < -f3 - this.planes[v].d) {
                return false;
            }
        }
        return true;
    }

    public boolean sphereInFrustumWithoutNearFar(Vector3 vector30, float f) {
        for(int v = 2; v < 6; ++v) {
            if(this.planes[v].normal.x * vector30.x + this.planes[v].normal.y * vector30.y + this.planes[v].normal.z * vector30.z < -f - this.planes[v].d) {
                return false;
            }
        }
        return true;
    }

    public void update(Matrix4 matrix40) {
        System.arraycopy(Frustum.clipSpacePlanePointsArray, 0, this.planePointsArray, 0, Frustum.clipSpacePlanePointsArray.length);
        Matrix4.prj(matrix40.val, this.planePointsArray, 0, 8, 3);
        int v1 = 0;
        for(int v = 0; v1 < 8; v += 3) {
            Vector3 vector30 = this.planePoints[v1];
            vector30.x = this.planePointsArray[v];
            vector30.y = this.planePointsArray[v + 1];
            vector30.z = this.planePointsArray[v + 2];
            ++v1;
        }
        this.planes[0].set(this.planePoints[1], this.planePoints[0], this.planePoints[2]);
        this.planes[1].set(this.planePoints[4], this.planePoints[5], this.planePoints[7]);
        this.planes[2].set(this.planePoints[0], this.planePoints[4], this.planePoints[3]);
        this.planes[3].set(this.planePoints[5], this.planePoints[1], this.planePoints[6]);
        this.planes[4].set(this.planePoints[2], this.planePoints[3], this.planePoints[6]);
        this.planes[5].set(this.planePoints[4], this.planePoints[0], this.planePoints[1]);
    }
}

