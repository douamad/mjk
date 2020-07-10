import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMaison } from 'app/shared/model/maison.model';
import { MaisonService } from './maison.service';

@Component({
  templateUrl: './maison-delete-dialog.component.html',
})
export class MaisonDeleteDialogComponent {
  maison?: IMaison;

  constructor(protected maisonService: MaisonService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.maisonService.delete(id).subscribe(() => {
      this.eventManager.broadcast('maisonListModification');
      this.activeModal.close();
    });
  }
}
