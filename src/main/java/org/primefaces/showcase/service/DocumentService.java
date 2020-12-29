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
package org.primefaces.showcase.service;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.showcase.domain.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class DocumentService {
    
    public TreeNode createDocuments() {
        TreeNode root = new DefaultTreeNode(new Document("Files", "-", "Folder"), null);

		TreeNode applications = new DefaultTreeNode(new Document("Applications", "100kb", "Folder"), root);
		TreeNode cloud = new DefaultTreeNode(new Document("Cloud", "20kb", "Folder"), root);
		TreeNode desktop = new DefaultTreeNode(new Document("Desktop", "150kb", "Folder"), root);
		TreeNode documents = new DefaultTreeNode(new Document("Documents", "75kb", "Folder"), root);
		TreeNode downloads = new DefaultTreeNode(new Document("Downloads", "25kb", "Folder"), root);
		TreeNode main = new DefaultTreeNode(new Document("Main", "50kb", "Folder"), root);
		TreeNode other = new DefaultTreeNode(new Document("Other", "5kb", "Folder"), root);
		TreeNode pictures = new DefaultTreeNode(new Document("Pictures", "150kb", "Folder"), root);
		TreeNode videos = new DefaultTreeNode(new Document("Videos", "1500kb", "Folder"), root);

		//Applications
		TreeNode primeface = new DefaultTreeNode(new Document("Primefaces", "25kb", "Folder"), applications);
		TreeNode primefacesapp = new DefaultTreeNode(new Document("primefaces.app", "10kb", "Application"), primeface);
		TreeNode nativeapp = new DefaultTreeNode(new Document("native.app", "10kb", "Application"), primeface);
		TreeNode mobileapp = new DefaultTreeNode(new Document("mobile.app", "5kb", "Application"), primeface);
		TreeNode editorapp = new DefaultTreeNode(new Document("editor.app", "25kb", "Application"), applications);
		TreeNode settingsapp = new DefaultTreeNode(new Document("settings.app", "50kb", "Application"), applications);

		//Cloud
		TreeNode backup1 = new DefaultTreeNode(new Document("backup-1.zip", "10kb", "Zip"), cloud);
		TreeNode backup2 = new DefaultTreeNode(new Document("backup-2.zip", "10kb", "Zip"), cloud);

		//Desktop
		TreeNode note1 = new DefaultTreeNode(new Document("note-meeting.txt", "50kb", "Text"), desktop);
		TreeNode note2 = new DefaultTreeNode(new Document("note-todo.txt", "100kb", "Text"), desktop);

		//Documents
		TreeNode work = new DefaultTreeNode(new Document("Work", "55kb", "Folder"), documents);
		TreeNode expenses = new DefaultTreeNode(new Document("Expenses.doc", "30kb", "Document"), work);
		TreeNode resume = new DefaultTreeNode(new Document("Resume.doc", "25kb", "Resume"), work);
		TreeNode home = new DefaultTreeNode(new Document("Home", "20kb", "Folder"), documents);
		TreeNode invoices = new DefaultTreeNode(new Document("Invoices", "20kb", "Text"), home);

		//Downloads
		TreeNode spanish = new DefaultTreeNode(new Document("Spanish", "10kb", "Folder"), downloads);
		TreeNode tutorial1 = new DefaultTreeNode(new Document("tutorial-a1.txt", "5kb", "Text"), spanish);
		TreeNode tutorial2 = new DefaultTreeNode(new Document("tutorial-a2.txt", "5kb", "Text"), spanish);
		TreeNode travel = new DefaultTreeNode(new Document("Travel", "15kb", "Folder"), downloads);
		TreeNode hotelpdf = new DefaultTreeNode(new Document("Hotel.pdf", "10kb", "PDF"), travel);
		TreeNode flightpdf = new DefaultTreeNode(new Document("Flight.pdf", "5kb", "PDF"), travel);

		//Main
		TreeNode bin = new DefaultTreeNode(new Document("bin", "50kb", "Link"), main);
		TreeNode etc = new DefaultTreeNode(new Document("etc", "100kb", "Link"), main);
		TreeNode var = new DefaultTreeNode(new Document("var", "100kb", "Link"), main);

		//Other
		TreeNode todotxt = new DefaultTreeNode(new Document("todo.txt", "3kb", "Text"), other);
		TreeNode logopng = new DefaultTreeNode(new Document("logo.png", "2kb", "Picture"), other);

		//Pictures
		TreeNode barcelona = new DefaultTreeNode(new Document("barcelona.jpg", "90kb", "Picture"), pictures);
		TreeNode primeng = new DefaultTreeNode(new Document("primefaces.png", "30kb", "Picture"), pictures);
		TreeNode prime = new DefaultTreeNode(new Document("prime.jpg", "30kb", "Picture"), pictures);

		//Videos
		TreeNode primefacesmkv = new DefaultTreeNode(new Document("primefaces.mkv", "1000kb", "Video"), videos);
		TreeNode introavi = new DefaultTreeNode(new Document("intro.avi", "500kb", "Video"), videos);

        return root;
    }
    
    public TreeNode createCheckboxDocuments() {
		TreeNode root = new CheckboxTreeNode(new Document("Files", "-", "Folder"), null);

		TreeNode applications = new CheckboxTreeNode(new Document("Applications", "100kb", "Folder"), root);
		TreeNode cloud = new CheckboxTreeNode(new Document("Cloud", "20kb", "Folder"), root);
		TreeNode desktop = new CheckboxTreeNode(new Document("Desktop", "150kb", "Folder"), root);
		TreeNode documents = new CheckboxTreeNode(new Document("Documents", "75kb", "Folder"), root);
		TreeNode downloads = new CheckboxTreeNode(new Document("Downloads", "25kb", "Folder"), root);
		TreeNode main = new CheckboxTreeNode(new Document("Main", "50kb", "Folder"), root);
		TreeNode other = new CheckboxTreeNode(new Document("Other", "5kb", "Folder"), root);
		TreeNode pictures = new CheckboxTreeNode(new Document("Pictures", "150kb", "Folder"), root);
		TreeNode videos = new CheckboxTreeNode(new Document("Videos", "1500kb", "Folder"), root);

		//Applications
		TreeNode primeface = new CheckboxTreeNode(new Document("Primefaces", "25kb", "Folder"), applications);
		TreeNode primefacesapp = new CheckboxTreeNode(new Document("primefaces.app", "10kb", "Application"), primeface);
		TreeNode nativeapp = new CheckboxTreeNode(new Document("native.app", "10kb", "Application"), primeface);
		TreeNode mobileapp = new CheckboxTreeNode(new Document("mobile.app", "5kb", "Application"), primeface);
		TreeNode editorapp = new CheckboxTreeNode(new Document("editor.app", "25kb", "Application"), applications);
		TreeNode settingsapp = new CheckboxTreeNode(new Document("settings.app", "50kb", "Application"), applications);

		//Cloud
		TreeNode backup1 = new CheckboxTreeNode(new Document("backup-1.zip", "10kb", "Zip"), cloud);
		TreeNode backup2 = new CheckboxTreeNode(new Document("backup-2.zip", "10kb", "Zip"), cloud);

		//Desktop
		TreeNode note1 = new CheckboxTreeNode(new Document("note-meeting.txt", "50kb", "Text"), desktop);
		TreeNode note2 = new CheckboxTreeNode(new Document("note-todo.txt", "100kb", "Text"), desktop);

		//Documents
		TreeNode work = new CheckboxTreeNode(new Document("Work", "55kb", "Folder"), documents);
		TreeNode expenses = new CheckboxTreeNode(new Document("Expenses.doc", "30kb", "Document"), work);
		TreeNode resume = new CheckboxTreeNode(new Document("Resume.doc", "25kb", "Resume"), work);
		TreeNode home = new CheckboxTreeNode(new Document("Home", "20kb", "Folder"), documents);
		TreeNode invoices = new CheckboxTreeNode(new Document("Invoices", "20kb", "Text"), home);

		//Downloads
		TreeNode spanish = new CheckboxTreeNode(new Document("Spanish", "10kb", "Folder"), downloads);
		TreeNode tutorial1 = new CheckboxTreeNode(new Document("tutorial-a1.txt", "5kb", "Text"), spanish);
		TreeNode tutorial2 = new CheckboxTreeNode(new Document("tutorial-a2.txt", "5kb", "Text"), spanish);
		TreeNode travel = new CheckboxTreeNode(new Document("Travel", "15kb", "Folder"), downloads);
		TreeNode hotelpdf = new CheckboxTreeNode(new Document("Hotel.pdf", "10kb", "PDF"), travel);
		TreeNode flightpdf = new CheckboxTreeNode(new Document("Flight.pdf", "5kb", "PDF"), travel);

		//Main
		TreeNode bin = new CheckboxTreeNode(new Document("bin", "50kb", "Link"), main);
		TreeNode etc = new CheckboxTreeNode(new Document("etc", "100kb", "Link"), main);
		TreeNode var = new CheckboxTreeNode(new Document("var", "100kb", "Link"), main);

		//Other
		TreeNode todotxt = new CheckboxTreeNode(new Document("todo.txt", "3kb", "Text"), other);
		TreeNode logopng = new CheckboxTreeNode(new Document("logo.png", "2kb", "Picture"), other);

		//Pictures
		TreeNode barcelona = new CheckboxTreeNode(new Document("barcelona.jpg", "90kb", "Picture"), pictures);
		TreeNode primeng = new CheckboxTreeNode(new Document("primefaces.png", "30kb", "Picture"), pictures);
		TreeNode prime = new CheckboxTreeNode(new Document("prime.jpg", "30kb", "Picture"), pictures);

		//Videos
		TreeNode primefacesmkv = new CheckboxTreeNode(new Document("primefaces.mkv", "1000kb", "Video"), videos);
		TreeNode introavi = new CheckboxTreeNode(new Document("intro.avi", "500kb", "Video"), videos);
        
        return root;
    }
}
