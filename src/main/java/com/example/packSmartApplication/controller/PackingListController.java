package com.example.packSmartApplication.controller;

import com.example.packSmartApplication.entity.PackingList;
import com.example.packSmartApplication.repository.PackingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PackingListController {

    @Autowired
    private PackingListRepository packingListRepository;

    @GetMapping("/")
    public String showPackingForm(Model model) {
        model.addAttribute("packingList", new PackingList());
        return "packingForm";
    }

    @PostMapping("/generatePackingList")
    public String generatePackingList(@ModelAttribute PackingList packingList, Model model) {
        // Generate packing suggestions based on destination, trip type, etc.
        List<String> packingItems = getPackingSuggestions(packingList);
        packingList.setItems(packingItems);

        // Save to database
        packingListRepository.save(packingList);

        // Display the packing list
        model.addAttribute("packingList", packingList);
        return "packingList";
    }

    private List<String> getPackingSuggestions(PackingList packingList) {
        List<String> items = new ArrayList<>();

        // Mock suggestions based on trip type and activities
        if (packingList.getTripType().equalsIgnoreCase("Adventure")) {
            items.add("Hiking Boots");
            items.add("Sunscreen");
            items.add("Water Bottle");
            items.add("Backpack");
            items.add("First Aid Kit");
            items.add("Trail Map");
            items.add("Energy Snacks");
            items.add("Headlamp");
        } else if (packingList.getTripType().equalsIgnoreCase("Business")) {
            items.add("Formal Suit");
            items.add("Laptop");
            items.add("Business Cards");
            items.add("Dress Shoes");
            items.add("Notepad");
            items.add("Portable Charger");
            items.add("Presentation Materials");
            items.add("Tie/Belt");
        } else if (packingList.getTripType().equalsIgnoreCase("Leisure")) {
            items.add("Casual Clothes");
            items.add("Comfortable Shoes");
            items.add("Camera");
            items.add("Travel Guidebook");
            items.add("Books/Magazines");
            items.add("Snacks");
        }

        // Add weather-based items (for simplicity, mock weather)
        if (packingList.getDestination().equalsIgnoreCase("Mountain")) {
            items.add("Jacket");
            items.add("Gloves");
            items.add("Thermal Underwear");
            items.add("Beanie");
            items.add("Snow Boots");
            items.add("Gaiters");
        } else if (packingList.getDestination().equalsIgnoreCase("Beach")) {
            items.add("Swimsuit");
            items.add("Sunglasses");
            items.add("Beach Towel");
            items.add("Flip Flops");
            items.add("Beach Umbrella");
            items.add("Snorkel Gear");
        } else if (packingList.getDestination().equalsIgnoreCase("City")) {
            items.add("Comfortable Walking Shoes");
            items.add("Light Jacket");
            items.add("Portable Phone Charger");
            items.add("City Map");
            items.add("Reusable Water Bottle");
        }

        // Add activity-based items
        if (packingList.getActivities().contains("Hiking")) {
            items.add("Hiking Gear");
            items.add("Trekking Poles");
            items.add("Insect Repellent");
            items.add("Compass");
        }
        if (packingList.getActivities().contains("Swimming")) {
            items.add("Swim Cap");
            items.add("Beach Bag");
            items.add("Waterproof Phone Case");
        }
        if (packingList.getActivities().contains("Sightseeing")) {
            items.add("Comfortable Backpack");
            items.add("Hat");
            items.add("Travel Journal");
        }
        if (packingList.getActivities().contains("Business Meetings")) {
            items.add("Laptop Charger");
            items.add("Projector (if required)");
            items.add("Presentation Clicker");
        }

        return items;
    }

}
