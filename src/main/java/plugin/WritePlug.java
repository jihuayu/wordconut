package plugin;

import com.jihuayu.wordcount.api.Plugin;
import com.jihuayu.wordcount.api.event.ReadOverEvent;
import com.jihuayu.wordcount.api.event.WriteEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class WritePlug implements Plugin {
    public WritePlug(){
        EventBus.getDefault().register(this);
    }
    @Override
    public void getCommandUsage(List<String> info) {
        //do need
    }

    @Override
    public String getCommandName() {
        return null;
    }

    @Override
    public void doCommand(String[] args) {
        //do need
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WriteEvent event){
        System.out.println(event.getText());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEven1t(ReadOverEvent event){
        System.out.println("end");
    }
}
