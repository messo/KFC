package hu.sch.kfc.client.place;

import hu.sch.kfc.client.model.ProgramProxy;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class EditProgramPlace extends Place {

    private final ProgramProxy program;
    private final Long programId;

    public EditProgramPlace(ProgramProxy program) {
        this.program = program;
        this.programId = program.getId();
    }

    public EditProgramPlace(Long programId) {
        this.program = null;
        this.programId = programId;
    }

    public ProgramProxy getProgram() {
        return program;
    }

    public Long getProgramId() {
        return programId;
    }

    @Prefix(value = "editProgram")
    public static class Tokenizer implements PlaceTokenizer<EditProgramPlace> {
        @Override
        public String getToken(EditProgramPlace place) {
            return place.getProgramId().toString();
        }

        @Override
        public EditProgramPlace getPlace(String token) {
            return new EditProgramPlace(Long.valueOf(token));
        }
    }
}
