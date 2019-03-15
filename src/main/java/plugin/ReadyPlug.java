package plugin;

import com.jihuayu.wordcount.api.Plugin;
import com.jihuayu.wordcount.api.event.ReadyEvent;
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
        //dont need
    }

    @Override
    public String getCommandName() {
        return null;
    }

    @Override
    public void doCommand(String[] args) {
        //dont need

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReadyEvent event){
        System.out.println("hello");
    }
}
