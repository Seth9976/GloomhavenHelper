package androidx.fragment.app;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class FragmentTransitionImpl {
    public abstract void addTarget(Object arg1, View arg2);

    public abstract void addTargets(Object arg1, ArrayList arg2);

    public abstract void beginDelayedTransition(ViewGroup arg1, Object arg2);

    protected static void bfsAddViewChildren(List list0, View view0) {
        int v = list0.size();
        if(FragmentTransitionImpl.containedBeforeIndex(list0, view0, v)) {
            return;
        }
        list0.add(view0);
        for(int v1 = v; v1 < list0.size(); ++v1) {
            View view1 = (View)list0.get(v1);
            if(view1 instanceof ViewGroup) {
                ViewGroup viewGroup0 = (ViewGroup)view1;
                int v2 = viewGroup0.getChildCount();
                for(int v3 = 0; v3 < v2; ++v3) {
                    View view2 = viewGroup0.getChildAt(v3);
                    if(!FragmentTransitionImpl.containedBeforeIndex(list0, view2, v)) {
                        list0.add(view2);
                    }
                }
            }
        }
    }

    public abstract boolean canHandle(Object arg1);

    void captureTransitioningViews(ArrayList arrayList0, View view0) {
        if(view0.getVisibility() == 0) {
            if(view0 instanceof ViewGroup) {
                if(ViewGroupCompat.isTransitionGroup(((ViewGroup)view0))) {
                    arrayList0.add(((ViewGroup)view0));
                    return;
                }
                int v = ((ViewGroup)view0).getChildCount();
                for(int v1 = 0; v1 < v; ++v1) {
                    this.captureTransitioningViews(arrayList0, ((ViewGroup)view0).getChildAt(v1));
                }
                return;
            }
            arrayList0.add(view0);
        }
    }

    public abstract Object cloneTransition(Object arg1);

    private static boolean containedBeforeIndex(List list0, View view0, int v) {
        for(int v1 = 0; v1 < v; ++v1) {
            if(list0.get(v1) == view0) {
                return true;
            }
        }
        return false;
    }

    static String findKeyForValue(Map map0, String s) {
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(s.equals(map$Entry0.getValue())) {
                return (String)map$Entry0.getKey();
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    void findNamedViews(Map map0, View view0) {
        if(view0.getVisibility() == 0) {
            String s = ViewCompat.getTransitionName(view0);
            if(s != null) {
                map0.put(s, view0);
            }
            if(view0 instanceof ViewGroup) {
                int v = ((ViewGroup)view0).getChildCount();
                for(int v1 = 0; v1 < v; ++v1) {
                    this.findNamedViews(map0, ((ViewGroup)view0).getChildAt(v1));
                }
            }
        }
    }

    protected void getBoundsOnScreen(View view0, Rect rect0) {
        int[] arr_v = new int[2];
        view0.getLocationOnScreen(arr_v);
        rect0.set(arr_v[0], arr_v[1], arr_v[0] + view0.getWidth(), arr_v[1] + view0.getHeight());
    }

    protected static boolean isNullOrEmpty(List list0) {
        return list0 == null || list0.isEmpty();
    }

    public abstract Object mergeTransitionsInSequence(Object arg1, Object arg2, Object arg3);

    public abstract Object mergeTransitionsTogether(Object arg1, Object arg2, Object arg3);

    ArrayList prepareSetNameOverridesReordered(ArrayList arrayList0) {
        ArrayList arrayList1 = new ArrayList();
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = (View)arrayList0.get(v1);
            arrayList1.add(ViewCompat.getTransitionName(view0));
            ViewCompat.setTransitionName(view0, null);
        }
        return arrayList1;
    }

    public abstract void removeTarget(Object arg1, View arg2);

    public abstract void replaceTargets(Object arg1, ArrayList arg2, ArrayList arg3);

    public abstract void scheduleHideFragmentView(Object arg1, View arg2, ArrayList arg3);

    void scheduleNameReset(ViewGroup viewGroup0, ArrayList arrayList0, Map map0) {
        OneShotPreDrawListener.add(viewGroup0, new Runnable() {
            @Override
            public void run() {
                int v = arrayList0.size();
                for(int v1 = 0; v1 < v; ++v1) {
                    View view0 = (View)arrayList0.get(v1);
                    String s = ViewCompat.getTransitionName(view0);
                    ViewCompat.setTransitionName(view0, ((String)map0.get(s)));
                }
            }
        });
    }

    public abstract void scheduleRemoveTargets(Object arg1, Object arg2, ArrayList arg3, Object arg4, ArrayList arg5, Object arg6, ArrayList arg7);

    public abstract void setEpicenter(Object arg1, Rect arg2);

    public abstract void setEpicenter(Object arg1, View arg2);

    void setNameOverridesOrdered(View view0, ArrayList arrayList0, Map map0) {
        OneShotPreDrawListener.add(view0, new Runnable() {
            @Override
            public void run() {
                int v = arrayList0.size();
                for(int v1 = 0; v1 < v; ++v1) {
                    View view0 = (View)arrayList0.get(v1);
                    String s = ViewCompat.getTransitionName(view0);
                    if(s != null) {
                        ViewCompat.setTransitionName(view0, FragmentTransitionImpl.findKeyForValue(map0, s));
                    }
                }
            }
        });
    }

    void setNameOverridesReordered(View view0, ArrayList arrayList0, ArrayList arrayList1, ArrayList arrayList2, Map map0) {
        int v = arrayList1.size();
        ArrayList arrayList3 = new ArrayList();
        for(int v1 = 0; v1 < v; ++v1) {
            View view1 = (View)arrayList0.get(v1);
            String s = ViewCompat.getTransitionName(view1);
            arrayList3.add(s);
            if(s != null) {
                ViewCompat.setTransitionName(view1, null);
                String s1 = (String)map0.get(s);
                for(int v2 = 0; v2 < v; ++v2) {
                    if(s1.equals(arrayList2.get(v2))) {
                        ViewCompat.setTransitionName(((View)arrayList1.get(v2)), s);
                        break;
                    }
                }
            }
        }
        OneShotPreDrawListener.add(view0, new Runnable() {
            @Override
            public void run() {
                for(int v = 0; v < v; ++v) {
                    ViewCompat.setTransitionName(((View)arrayList1.get(v)), ((String)arrayList2.get(v)));
                    ViewCompat.setTransitionName(((View)arrayList0.get(v)), ((String)arrayList3.get(v)));
                }
            }
        });
    }

    public abstract void setSharedElementTargets(Object arg1, View arg2, ArrayList arg3);

    public abstract void swapSharedElementTargets(Object arg1, ArrayList arg2, ArrayList arg3);

    public abstract Object wrapTransitionInSet(Object arg1);
}

