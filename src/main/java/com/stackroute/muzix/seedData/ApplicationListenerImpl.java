package com.stackroute.muzix.seedData;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerImpl implements ApplicationListener<ContextRefreshedEvent> {

    private TrackRepository trackRepository;

    @Autowired
    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        trackRepository.save(new Track(1,"Havana","hello tune"));
        trackRepository.save(new Track(2,"Something just like this","fantasy"));
    }
}
