package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.BlockDto;
import com.example.codingbat.entity.*;
import com.example.codingbat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockService {
    @Autowired
    BlockRepository blockRepository;
    @Autowired
    DeclareRepository declareRepository;
    @Autowired
    ThemeRepository themeRepository;


    //      Create
    public ApiResponse addBlock(BlockDto blockDto) {
        Block block = new Block();
        block.setName(blockDto.getName());
        block.setNumberStar(blockDto.getNumberStar());

        Optional<Declare> optionalDeclare = declareRepository.findById(blockDto.getDeclare());
        if (!optionalDeclare.isPresent())
            return new ApiResponse("Not found", false);
        block.setDeclare(optionalDeclare.get());

        Optional<Theme> optionalTheme = themeRepository.findById(blockDto.getTheme());
        if (!optionalTheme.isPresent())
            return new ApiResponse("Not found", false);
        block.setTheme(optionalTheme.get());

        blockRepository.save(block);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<Block> getBlock() {
        return blockRepository.findAll();
    }

    //    Get by id
    public Block getBlockById(Integer id) {
        Optional<Block> optionalBlock = blockRepository.findById(id);
        if (!optionalBlock.isPresent())
            return null;
        return optionalBlock.get();
    }

    //    Update
    public ApiResponse editBlock(Integer id, BlockDto blockDto) {
        Optional<Block> optionalBlock = blockRepository.findById(id);
        if (!optionalBlock.isPresent())
            return new ApiResponse("Not found", false);
        Block editBLock = optionalBlock.get();
        editBLock.setName(blockDto.getName());
        editBLock.setNumberStar(blockDto.getNumberStar());

        Optional<Declare> optionalDeclare = declareRepository.findById(blockDto.getDeclare());
        if (!optionalDeclare.isPresent())
            return new ApiResponse("Not found", false);
        editBLock.setDeclare(optionalDeclare.get());

        Optional<Theme> optionalTheme = themeRepository.findById(blockDto.getTheme());
        if (!optionalTheme.isPresent())
            return new ApiResponse("Not found", false);
        editBLock.setTheme(optionalTheme.get());

        blockRepository.save(editBLock);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteBlock(Integer id) {
        try {
            blockRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}
