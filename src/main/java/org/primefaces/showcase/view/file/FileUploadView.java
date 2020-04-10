/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.view.file;

import org.primefaces.event.FileChunkUploadEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFileChunk;
import org.primefaces.model.file.UploadedFiles;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Named
@RequestScoped
public class FileUploadView {
    
    private UploadedFile file;
    private UploadedFiles files;

    private boolean writeChunkUpload2TempDir = false;
    private String tempDir = System.getProperty("java.io.tmpdir");

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFiles getFiles() {
        return files;
    }

    public void setFiles(UploadedFiles files) {
        this.files = files;
    }

    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void uploadMultiple() {
        if (files != null) {
            for (UploadedFile f : files.getFiles()) {
                FacesMessage message = new FacesMessage("Successful", f.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void handleFileChunkUpload(FileChunkUploadEvent event) {
        UploadedFileChunk uploadedFileChunk = event.getFileChunk();

        //TODO: we see this message after the complete file is uploaded. Is there a chance to get this update to the client after each chunk?
        FacesMessage msg = new FacesMessage("Chunk Successful", "Chunk " + uploadedFileChunk.getChunkRangeBegin() +
                " - " + uploadedFileChunk.getChunkRangeEnd() +
                " of file " + uploadedFileChunk.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        if (writeChunkUpload2TempDir) {
            try {
                OpenOption openOption = StandardOpenOption.APPEND;
                if (uploadedFileChunk.getChunkRangeBegin() == 0) {
                    openOption = StandardOpenOption.CREATE;
                }

                Files.write(Paths.get(tempDir, uploadedFileChunk.getFileName()), uploadedFileChunk.getContent(), openOption);
            }
            catch (IOException ex) {
                msg = new FacesMessage("Chunk Error", "Chunk " + uploadedFileChunk.getChunkRangeBegin() +
                        " - " + uploadedFileChunk.getChunkRangeEnd() +
                        " of file " + uploadedFileChunk.getFileName() + " can´t be written to tempDir. " + ex.getMessage());
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        if (writeChunkUpload2TempDir) {
            try {
                //cleanup tempDir
                Files.delete(Paths.get(tempDir, event.getFile().getFileName()));
            }
            catch (IOException ex) {
                msg = new FacesMessage("Error", event.getFile().getFileName() + " can´t be deleted from tempDir. " + ex.getMessage());
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
}
