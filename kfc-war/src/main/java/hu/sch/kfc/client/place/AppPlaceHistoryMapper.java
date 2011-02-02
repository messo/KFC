package hu.sch.kfc.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ ListGroupsPlace.Tokenizer.class, ShowGroupPlace.Tokenizer.class,
        ShowProgramPlace.Tokenizer.class, EditProgramPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
