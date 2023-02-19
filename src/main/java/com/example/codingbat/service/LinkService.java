package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.Link;
import com.example.codingbat.repository.DoneRepository;
import com.example.codingbat.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService {
    @Autowired
    LinkRepository linkRepository;


    //      Create
    public ApiResponse addLink(Link link) {
        Link link1 = new Link();
        link1.setLink(link.getLink());

        linkRepository.save(link1);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<Link> getLink() {
        return linkRepository.findAll();
    }

    //    Get by id
    public Link getLinkById(Integer id) {
        Optional<Link> optionalLink = linkRepository.findById(id);
        if (!optionalLink.isPresent())
            return null;
        return optionalLink.get();
    }

    //    Update
    public ApiResponse editLink(Integer id, Link link) {
        Optional<Link> optionalLink = linkRepository.findById(id);
        if (!optionalLink.isPresent())
            return new ApiResponse("Not found", false);
        Link editLink = optionalLink.get();
        editLink.setLink(link.getLink());

        linkRepository.save(editLink);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteLink(Integer id) {
        try {
            linkRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}
