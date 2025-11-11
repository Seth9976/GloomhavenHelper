package com.badlogic.gdx.utils;

import java.util.Arrays;

public class BinaryHeap {
    public static class Node {
        int index;
        float value;

        public Node(float f) {
            this.value = f;
        }

        public float getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return Float.toString(this.value);
        }
    }

    private final boolean isMaxHeap;
    private Node[] nodes;
    public int size;

    public BinaryHeap() {
        this(16, false);
    }

    public BinaryHeap(int v, boolean z) {
        this.isMaxHeap = z;
        this.nodes = new Node[v];
    }

    public Node add(Node binaryHeap$Node0) {
        int v = this.size;
        Node[] arr_binaryHeap$Node = this.nodes;
        if(v == arr_binaryHeap$Node.length) {
            Node[] arr_binaryHeap$Node1 = new Node[v << 1];
            System.arraycopy(arr_binaryHeap$Node, 0, arr_binaryHeap$Node1, 0, v);
            this.nodes = arr_binaryHeap$Node1;
        }
        int v1 = this.size;
        binaryHeap$Node0.index = v1;
        this.nodes[v1] = binaryHeap$Node0;
        this.size = v1 + 1;
        this.up(v1);
        return binaryHeap$Node0;
    }

    public Node add(Node binaryHeap$Node0, float f) {
        binaryHeap$Node0.value = f;
        return this.add(binaryHeap$Node0);
    }

    public void clear() {
        Arrays.fill(this.nodes, 0, this.size, null);
        this.size = 0;
    }

    public boolean contains(Node binaryHeap$Node0, boolean z) {
        if(binaryHeap$Node0 == null) {
            throw new IllegalArgumentException("node cannot be null.");
        }
        if(z) {
            Node[] arr_binaryHeap$Node = this.nodes;
            for(int v = 0; v < arr_binaryHeap$Node.length; ++v) {
                if(arr_binaryHeap$Node[v] == binaryHeap$Node0) {
                    return true;
                }
            }
            return false;
        }
        Node[] arr_binaryHeap$Node1 = this.nodes;
        for(int v1 = 0; v1 < arr_binaryHeap$Node1.length; ++v1) {
            if(arr_binaryHeap$Node1[v1].equals(binaryHeap$Node0)) {
                return true;
            }
        }
        return false;
    }

    private void down(int v) {
        float f2;
        Node[] arr_binaryHeap$Node = this.nodes;
        int v1 = this.size;
        Node binaryHeap$Node0 = arr_binaryHeap$Node[v];
        float f = binaryHeap$Node0.value;
        while(true) {
            boolean z = true;
            int v2 = (v << 1) + 1;
            if(v2 >= v1) {
                break;
            }
            Node binaryHeap$Node1 = null;
            Node binaryHeap$Node2 = arr_binaryHeap$Node[v2];
            float f1 = binaryHeap$Node2.value;
            if(v2 + 1 < v1) {
                binaryHeap$Node1 = arr_binaryHeap$Node[v2 + 1];
                f2 = binaryHeap$Node1.value;
            }
            else if(this.isMaxHeap) {
                f2 = -3.402823E+38f;
            }
            else {
                f2 = 3.402823E+38f;
            }
            if((f1 < f2 ^ this.isMaxHeap) == 0) {
                if(f2 == f) {
                    break;
                }
                if(f2 <= f) {
                    z = false;
                }
                if((this.isMaxHeap ^ z) != 0) {
                    break;
                }
                arr_binaryHeap$Node[v] = binaryHeap$Node1;
                if(binaryHeap$Node1 != null) {
                    binaryHeap$Node1.index = v;
                }
                v = v2 + 1;
            }
            else {
                if(f1 == f) {
                    break;
                }
                if(f1 <= f) {
                    z = false;
                }
                if((z ^ this.isMaxHeap) != 0) {
                    break;
                }
                arr_binaryHeap$Node[v] = binaryHeap$Node2;
                binaryHeap$Node2.index = v;
                v = v2;
            }
        }
        arr_binaryHeap$Node[v] = binaryHeap$Node0;
        binaryHeap$Node0.index = v;
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof BinaryHeap)) {
            return false;
        }
        int v = this.size;
        if(((BinaryHeap)object0).size != v) {
            return false;
        }
        Node[] arr_binaryHeap$Node = this.nodes;
        Node[] arr_binaryHeap$Node1 = ((BinaryHeap)object0).nodes;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_binaryHeap$Node[v1].value != arr_binaryHeap$Node1[v1].value) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        Node[] arr_binaryHeap$Node = this.nodes;
        int v = this.size;
        int v1 = 1;
        for(int v2 = 0; v2 < v; ++v2) {
            v1 = v1 * 0x1F + Float.floatToIntBits(arr_binaryHeap$Node[v2].value);
        }
        return v1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public Node peek() {
        if(this.size == 0) {
            throw new IllegalStateException("The heap is empty.");
        }
        return this.nodes[0];
    }

    public Node pop() {
        Node[] arr_binaryHeap$Node = this.nodes;
        Node binaryHeap$Node0 = arr_binaryHeap$Node[0];
        int v = this.size - 1;
        this.size = v;
        if(v > 0) {
            int v1 = this.size;
            arr_binaryHeap$Node[0] = arr_binaryHeap$Node[v1];
            arr_binaryHeap$Node[v1] = null;
            this.down(0);
            return binaryHeap$Node0;
        }
        arr_binaryHeap$Node[0] = null;
        return binaryHeap$Node0;
    }

    public Node remove(Node binaryHeap$Node0) {
        boolean z = true;
        int v = this.size - 1;
        this.size = v;
        if(v > 0) {
            Node[] arr_binaryHeap$Node = this.nodes;
            int v1 = this.size;
            Node binaryHeap$Node1 = arr_binaryHeap$Node[v1];
            arr_binaryHeap$Node[v1] = null;
            arr_binaryHeap$Node[binaryHeap$Node0.index] = binaryHeap$Node1;
            if(binaryHeap$Node1.value >= binaryHeap$Node0.value) {
                z = false;
            }
            if((this.isMaxHeap ^ z) != 0) {
                this.up(binaryHeap$Node0.index);
                return binaryHeap$Node0;
            }
            this.down(binaryHeap$Node0.index);
            return binaryHeap$Node0;
        }
        this.nodes[0] = null;
        return binaryHeap$Node0;
    }

    public void setValue(Node binaryHeap$Node0, float f) {
        float f1 = binaryHeap$Node0.value;
        binaryHeap$Node0.value = f;
        if((f < f1 ^ this.isMaxHeap) != 0) {
            this.up(binaryHeap$Node0.index);
            return;
        }
        this.down(binaryHeap$Node0.index);
    }

    @Override
    public String toString() {
        if(this.size == 0) {
            return "[]";
        }
        Node[] arr_binaryHeap$Node = this.nodes;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append('[');
        stringBuilder0.append(arr_binaryHeap$Node[0].value);
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(", ");
            stringBuilder0.append(arr_binaryHeap$Node[v].value);
        }
        stringBuilder0.append(']');
        return "";
    }

    private void up(int v) {
        Node[] arr_binaryHeap$Node = this.nodes;
        Node binaryHeap$Node0 = arr_binaryHeap$Node[v];
        float f = binaryHeap$Node0.value;
        while(v > 0) {
            boolean z = true;
            int v1 = v - 1 >> 1;
            Node binaryHeap$Node1 = arr_binaryHeap$Node[v1];
            if(f >= binaryHeap$Node1.value) {
                z = false;
            }
            if((z ^ this.isMaxHeap) == 0) {
                break;
            }
            arr_binaryHeap$Node[v] = binaryHeap$Node1;
            binaryHeap$Node1.index = v;
            v = v1;
        }
        arr_binaryHeap$Node[v] = binaryHeap$Node0;
        binaryHeap$Node0.index = v;
    }
}

