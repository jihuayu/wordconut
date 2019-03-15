package plugin;

import com.jihuayu.wordcount.api.Plugin;
import com.jihuayu.wordcount.api.event.ReadEvent;
import com.jihuayu.wordcount.api.event.WriteEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class CountPlug implements Plugin {
    public CountPlug(){
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
    public void onMessageEvent(ReadEvent event){
        System.out.println(event.getText());
        EventBus.getDefault().post(new WriteEvent("has "+event.getText().length()));
    }
}
