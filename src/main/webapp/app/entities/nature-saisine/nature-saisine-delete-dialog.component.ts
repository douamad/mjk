import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INatureSaisine } from 'app/shared/model/nature-saisine.model';
import { NatureSaisineService } from './nature-saisine.service';

@Component({
  templateUrl: './nature-saisine-delete-dialog.component.html',
})
export class NatureSaisineDeleteDialogComponent {
  natureSaisine?: INatureSaisine;

  constructor(
    protected natureSaisineService: NatureSaisineService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.natureSaisineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('natureSaisineListModification');
      this.activeModal.close();
    });
  }
}
