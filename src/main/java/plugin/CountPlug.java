package plugin;

import com.jihuayu.core.api.Plugin;
import com.jihuayu.core.api.event.ReadEvent;
import com.jihuayu.core.api.event.ReadyEvent;
import com.jihuayu.core.api.event.WriteEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class CountPlug implements Plugin {
    private int charNum;
    public CountPlug(){
        EventBus.getDefault().register(this);
    }

    @Override
    public void getCommandUsage(List<String> info) {

    }

    @Override
    public String getCommandName() {
        return null;
    }

    @Override
    public void loadCommand(String[] args) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReadEvent event){
        System.out.println(event.getText());
        EventBus.getDefault().post(new WriteEvent("has"+event.getText().length()));
    }
}
