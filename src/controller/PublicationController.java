package src.controller;

import src.model.Publication;
import java.util.ArrayList;
import java.util.List;

public class PublicationController {
    private List<Publication> publications = new ArrayList<>();

    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    public void editPublication(int id, Publication newPublication) {
        for (Publication publication : publications) {
            if (publication.getId() == id) {
                publication.setName(newPublication.getName());
                break;
            }
        }
    }

    public void deletePublication(int id) {
        publications.removeIf(publication -> publication.getId() == id);
    }

    public Publication getPublication(int id) {
        for (Publication publication : publications) {
            if (publication.getId() == id) {
                return publication;
            }
        }
        return null;
    }

    public List<Publication> getAllPublications() {
        return publications;
    }
}
