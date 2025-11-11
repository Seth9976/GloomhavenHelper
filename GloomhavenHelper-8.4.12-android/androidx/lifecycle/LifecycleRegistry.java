package androidx.lifecycle;

import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap.IteratorWithAdditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class LifecycleRegistry extends Lifecycle {
    static class ObserverWithState {
        GenericLifecycleObserver mLifecycleObserver;
        State mState;

        ObserverWithState(LifecycleObserver lifecycleObserver0, State lifecycle$State0) {
            this.mLifecycleObserver = Lifecycling.getCallback(lifecycleObserver0);
            this.mState = lifecycle$State0;
        }

        void dispatchEvent(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
            State lifecycle$State0 = LifecycleRegistry.getStateAfter(lifecycle$Event0);
            this.mState = LifecycleRegistry.min(this.mState, lifecycle$State0);
            this.mLifecycleObserver.onStateChanged(lifecycleOwner0, lifecycle$Event0);
            this.mState = lifecycle$State0;
        }
    }

    private static final String LOG_TAG = "LifecycleRegistry";
    private int mAddingObserverCounter;
    private boolean mHandlingEvent;
    private final WeakReference mLifecycleOwner;
    private boolean mNewEventOccurred;
    private FastSafeIterableMap mObserverMap;
    private ArrayList mParentStates;
    private State mState;

    public LifecycleRegistry(@NonNull LifecycleOwner lifecycleOwner0) {
        this.mObserverMap = new FastSafeIterableMap();
        this.mAddingObserverCounter = 0;
        this.mHandlingEvent = false;
        this.mNewEventOccurred = false;
        this.mParentStates = new ArrayList();
        this.mLifecycleOwner = new WeakReference(lifecycleOwner0);
        this.mState = State.INITIALIZED;
    }

    @Override  // androidx.lifecycle.Lifecycle
    public void addObserver(@NonNull LifecycleObserver lifecycleObserver0) {
        ObserverWithState lifecycleRegistry$ObserverWithState0 = new ObserverWithState(lifecycleObserver0, (this.mState == State.DESTROYED ? State.DESTROYED : State.INITIALIZED));
        if(((ObserverWithState)this.mObserverMap.putIfAbsent(lifecycleObserver0, lifecycleRegistry$ObserverWithState0)) != null) {
            return;
        }
        LifecycleOwner lifecycleOwner0 = (LifecycleOwner)this.mLifecycleOwner.get();
        if(lifecycleOwner0 == null) {
            return;
        }
        boolean z = this.mAddingObserverCounter != 0 || this.mHandlingEvent;
        State lifecycle$State0 = this.calculateTargetState(lifecycleObserver0);
        ++this.mAddingObserverCounter;
        while(lifecycleRegistry$ObserverWithState0.mState.compareTo(lifecycle$State0) < 0 && this.mObserverMap.contains(lifecycleObserver0)) {
            this.pushParentState(lifecycleRegistry$ObserverWithState0.mState);
            lifecycleRegistry$ObserverWithState0.dispatchEvent(lifecycleOwner0, LifecycleRegistry.upEvent(lifecycleRegistry$ObserverWithState0.mState));
            this.popParentState();
            lifecycle$State0 = this.calculateTargetState(lifecycleObserver0);
        }
        if(!z) {
            this.sync();
        }
        --this.mAddingObserverCounter;
    }

    private void backwardPass(LifecycleOwner lifecycleOwner0) {
        Iterator iterator0 = this.mObserverMap.descendingIterator();
        while(iterator0.hasNext() && !this.mNewEventOccurred) {
            Object object0 = iterator0.next();
            ObserverWithState lifecycleRegistry$ObserverWithState0 = (ObserverWithState)((Map.Entry)object0).getValue();
            while(lifecycleRegistry$ObserverWithState0.mState.compareTo(this.mState) > 0 && !this.mNewEventOccurred && this.mObserverMap.contains(((Map.Entry)object0).getKey())) {
                Event lifecycle$Event0 = LifecycleRegistry.downEvent(lifecycleRegistry$ObserverWithState0.mState);
                this.pushParentState(LifecycleRegistry.getStateAfter(lifecycle$Event0));
                lifecycleRegistry$ObserverWithState0.dispatchEvent(lifecycleOwner0, lifecycle$Event0);
                this.popParentState();
            }
        }
    }

    private State calculateTargetState(LifecycleObserver lifecycleObserver0) {
        Map.Entry map$Entry0 = this.mObserverMap.ceil(lifecycleObserver0);
        State lifecycle$State0 = null;
        State lifecycle$State1 = map$Entry0 == null ? null : ((ObserverWithState)map$Entry0.getValue()).mState;
        if(!this.mParentStates.isEmpty()) {
            lifecycle$State0 = (State)this.mParentStates.get(this.mParentStates.size() - 1);
        }
        return LifecycleRegistry.min(LifecycleRegistry.min(this.mState, lifecycle$State1), lifecycle$State0);
    }

    private static Event downEvent(State lifecycle$State0) {
        switch(androidx.lifecycle.LifecycleRegistry.1.$SwitchMap$androidx$lifecycle$Lifecycle$State[lifecycle$State0.ordinal()]) {
            case 1: {
                throw new IllegalArgumentException();
            }
            case 2: {
                return Event.ON_DESTROY;
            }
            case 3: {
                return Event.ON_STOP;
            }
            case 4: {
                return Event.ON_PAUSE;
            }
            case 5: {
                throw new IllegalArgumentException();
            }
            default: {
                throw new IllegalArgumentException("Unexpected state value " + lifecycle$State0);
            }
        }
    }

    private void forwardPass(LifecycleOwner lifecycleOwner0) {
        IteratorWithAdditions safeIterableMap$IteratorWithAdditions0 = this.mObserverMap.iteratorWithAdditions();
        while(safeIterableMap$IteratorWithAdditions0.hasNext() && !this.mNewEventOccurred) {
            Object object0 = safeIterableMap$IteratorWithAdditions0.next();
            ObserverWithState lifecycleRegistry$ObserverWithState0 = (ObserverWithState)((Map.Entry)object0).getValue();
            while(lifecycleRegistry$ObserverWithState0.mState.compareTo(this.mState) < 0 && !this.mNewEventOccurred && this.mObserverMap.contains(((Map.Entry)object0).getKey())) {
                this.pushParentState(lifecycleRegistry$ObserverWithState0.mState);
                lifecycleRegistry$ObserverWithState0.dispatchEvent(lifecycleOwner0, LifecycleRegistry.upEvent(lifecycleRegistry$ObserverWithState0.mState));
                this.popParentState();
            }
        }
    }

    @Override  // androidx.lifecycle.Lifecycle
    @NonNull
    public State getCurrentState() {
        return this.mState;
    }

    public int getObserverCount() {
        return this.mObserverMap.size();
    }

    static State getStateAfter(Event lifecycle$Event0) {
        switch(androidx.lifecycle.LifecycleRegistry.1.$SwitchMap$androidx$lifecycle$Lifecycle$Event[lifecycle$Event0.ordinal()]) {
            case 1: 
            case 2: {
                return State.CREATED;
            }
            case 3: 
            case 4: {
                return State.STARTED;
            }
            case 5: {
                return State.RESUMED;
            }
            case 6: {
                return State.DESTROYED;
            }
            default: {
                throw new IllegalArgumentException("Unexpected event value " + lifecycle$Event0);
            }
        }
    }

    public void handleLifecycleEvent(@NonNull Event lifecycle$Event0) {
        this.moveToState(LifecycleRegistry.getStateAfter(lifecycle$Event0));
    }

    private boolean isSynced() {
        if(this.mObserverMap.size() == 0) {
            return true;
        }
        State lifecycle$State0 = ((ObserverWithState)this.mObserverMap.eldest().getValue()).mState;
        State lifecycle$State1 = ((ObserverWithState)this.mObserverMap.newest().getValue()).mState;
        return lifecycle$State0 == lifecycle$State1 && this.mState == lifecycle$State1;
    }

    @MainThread
    public void markState(@NonNull State lifecycle$State0) {
        this.moveToState(lifecycle$State0);
    }

    static State min(@NonNull State lifecycle$State0, @Nullable State lifecycle$State1) {
        return lifecycle$State1 == null || lifecycle$State1.compareTo(lifecycle$State0) >= 0 ? lifecycle$State0 : lifecycle$State1;
    }

    private void moveToState(State lifecycle$State0) {
        if(this.mState == lifecycle$State0) {
            return;
        }
        this.mState = lifecycle$State0;
        if(!this.mHandlingEvent && this.mAddingObserverCounter == 0) {
            this.mHandlingEvent = true;
            this.sync();
            this.mHandlingEvent = false;
            return;
        }
        this.mNewEventOccurred = true;
    }

    private void popParentState() {
        this.mParentStates.remove(this.mParentStates.size() - 1);
    }

    private void pushParentState(State lifecycle$State0) {
        this.mParentStates.add(lifecycle$State0);
    }

    @Override  // androidx.lifecycle.Lifecycle
    public void removeObserver(@NonNull LifecycleObserver lifecycleObserver0) {
        this.mObserverMap.remove(lifecycleObserver0);
    }

    private void sync() {
        LifecycleOwner lifecycleOwner0 = (LifecycleOwner)this.mLifecycleOwner.get();
        if(lifecycleOwner0 == null) {
            Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn\'t try dispatch new events from it.");
            return;
        }
        while(!this.isSynced()) {
            this.mNewEventOccurred = false;
            if(this.mState.compareTo(((ObserverWithState)this.mObserverMap.eldest().getValue()).mState) < 0) {
                this.backwardPass(lifecycleOwner0);
            }
            Map.Entry map$Entry0 = this.mObserverMap.newest();
            if(!this.mNewEventOccurred && map$Entry0 != null && this.mState.compareTo(((ObserverWithState)map$Entry0.getValue()).mState) > 0) {
                this.forwardPass(lifecycleOwner0);
            }
        }
        this.mNewEventOccurred = false;
    }

    private static Event upEvent(State lifecycle$State0) {
        switch(androidx.lifecycle.LifecycleRegistry.1.$SwitchMap$androidx$lifecycle$Lifecycle$State[lifecycle$State0.ordinal()]) {
            case 2: {
                return Event.ON_START;
            }
            case 3: {
                return Event.ON_RESUME;
            }
            case 4: {
                throw new IllegalArgumentException();
            }
            case 1: 
            case 5: {
                return Event.ON_CREATE;
            }
            default: {
                throw new IllegalArgumentException("Unexpected state value " + lifecycle$State0);
            }
        }
    }
}

