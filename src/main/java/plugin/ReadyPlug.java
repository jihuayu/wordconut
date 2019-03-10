package plugin;

import com.jihuayu.core.api.Plugin;
import com.jihuayu.core.api.event.ReadEvent;
import com.jihuayu.core.api.event.ReadyEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class ReadyPlug implements Plugin {
    public ReadyPlug(){
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
    public void onMessageEvent(ReadyEvent event){
        System.out.println(11111121);
    }
}
