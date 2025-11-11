package androidx.coordinatorlayout.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools.Pool;
import androidx.core.util.Pools.SimplePool;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestrictTo({Scope.LIBRARY})
public final class DirectedAcyclicGraph {
    private final SimpleArrayMap mGraph;
    private final Pool mListPool;
    private final ArrayList mSortResult;
    private final HashSet mSortTmpMarked;

    public DirectedAcyclicGraph() {
        this.mListPool = new SimplePool(10);
        this.mGraph = new SimpleArrayMap();
        this.mSortResult = new ArrayList();
        this.mSortTmpMarked = new HashSet();
    }

    public void addEdge(@NonNull Object object0, @NonNull Object object1) {
        if(!this.mGraph.containsKey(object0) || !this.mGraph.containsKey(object1)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList arrayList0 = (ArrayList)this.mGraph.get(object0);
        if(arrayList0 == null) {
            arrayList0 = this.getEmptyList();
            this.mGraph.put(object0, arrayList0);
        }
        arrayList0.add(object1);
    }

    public void addNode(@NonNull Object object0) {
        if(!this.mGraph.containsKey(object0)) {
            this.mGraph.put(object0, null);
        }
    }

    public void clear() {
        int v = this.mGraph.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ArrayList arrayList0 = (ArrayList)this.mGraph.valueAt(v1);
            if(arrayList0 != null) {
                this.poolList(arrayList0);
            }
        }
        this.mGraph.clear();
    }

    public boolean contains(@NonNull Object object0) {
        return this.mGraph.containsKey(object0);
    }

    private void dfs(Object object0, ArrayList arrayList0, HashSet hashSet0) {
        if(arrayList0.contains(object0)) {
            return;
        }
        if(hashSet0.contains(object0)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet0.add(object0);
        ArrayList arrayList1 = (ArrayList)this.mGraph.get(object0);
        if(arrayList1 != null) {
            int v1 = arrayList1.size();
            for(int v = 0; v < v1; ++v) {
                this.dfs(arrayList1.get(v), arrayList0, hashSet0);
            }
        }
        hashSet0.remove(object0);
        arrayList0.add(object0);
    }

    @NonNull
    private ArrayList getEmptyList() {
        ArrayList arrayList0 = (ArrayList)this.mListPool.acquire();
        return arrayList0 == null ? new ArrayList() : arrayList0;
    }

    @Nullable
    public List getIncomingEdges(@NonNull Object object0) {
        return (List)this.mGraph.get(object0);
    }

    @Nullable
    public List getOutgoingEdges(@NonNull Object object0) {
        int v = this.mGraph.size();
        List list0 = null;
        for(int v1 = 0; v1 < v; ++v1) {
            ArrayList arrayList0 = (ArrayList)this.mGraph.valueAt(v1);
            if(arrayList0 != null && arrayList0.contains(object0)) {
                if(list0 == null) {
                    list0 = new ArrayList();
                }
                ((ArrayList)list0).add(this.mGraph.keyAt(v1));
            }
        }
        return list0;
    }

    @NonNull
    public ArrayList getSortedList() {
        this.mSortResult.clear();
        this.mSortTmpMarked.clear();
        int v = this.mGraph.size();
        for(int v1 = 0; v1 < v; ++v1) {
            this.dfs(this.mGraph.keyAt(v1), this.mSortResult, this.mSortTmpMarked);
        }
        return this.mSortResult;
    }

    public boolean hasOutgoingEdges(@NonNull Object object0) {
        int v = this.mGraph.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ArrayList arrayList0 = (ArrayList)this.mGraph.valueAt(v1);
            if(arrayList0 != null && arrayList0.contains(object0)) {
                return true;
            }
        }
        return false;
    }

    private void poolList(@NonNull ArrayList arrayList0) {
        arrayList0.clear();
        this.mListPool.release(arrayList0);
    }

    int size() {
        return this.mGraph.size();
    }
}

