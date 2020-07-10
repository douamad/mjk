import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISaisine } from 'app/shared/model/saisine.model';
import { SaisineService } from './saisine.service';

@Component({
  templateUrl: './saisine-delete-dialog.component.html',
})
export class SaisineDeleteDialogComponent {
  saisine?: ISaisine;

  constructor(protected saisineService: SaisineService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.saisineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('saisineListModification');
      this.activeModal.close();
    });
  }
}
